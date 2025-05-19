package com.web.www.otherBusiness.service.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson2.JSONObject;
import com.web.www.otherBusiness.model.ResultRespVo;
import com.web.www.otherBusiness.model.SalaryReqVo;
import com.web.www.otherBusiness.model.User;
import com.web.www.otherBusiness.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private HttpServletRequest request;


    // 请求前缀地址
    private final String URL_PREFIX = "https://applet.greenhemei.com";

    // 登录接口
    private final String LOGIN_URL = "/app/index";

    // 获取工资接口
    private final String GET_SALARY_URL = "/SalaryAction/doSalaryData";


    /**
     * 获取工资
     *
     * @param reqVo  请求参数
     * @param isSelf 是否是自己，true表示自己，false表示班组的所有人
     * @return 获取结果
     */
    @Override
    public BigDecimal getSalary(SalaryReqVo reqVo, boolean isSelf) {
        // 请求url
        String baseRequestUrl = URL_PREFIX + GET_SALARY_URL;
        if (isSelf) {
            baseRequestUrl += "?self=true&";
        }
        // 存储token的key
        String tokenKey = "token:" + reqVo.getUserName() + ":";
        // 获取session
        HttpSession session = request.getSession();
        // 从session中获取token
        Object token = session.getAttribute(tokenKey);
        // 判断token是否为空，为空则获取token
        if (token == null) {
            token = getToken(new User(reqVo.getUserName(), reqVo.getUserPass()));
            // 将token放入session
            session.setAttribute("token:" + reqVo.getUserName() + ":", token);
        }
        // 初始值：0
        final BigDecimal[] salary = {BigDecimal.ZERO};
        // 获取时间范围
        List<String> dateList = getDateRangeList(reqVo.getStartTime(), reqVo.getEndTime());
        // 使用并行流做数据处理
        final String[] finalBaseRequestUrl = {baseRequestUrl};
        String finalToken = token.toString();
        dateList.parallelStream().forEach(time -> {
            if (!finalBaseRequestUrl[0].contains("?")) {
                finalBaseRequestUrl[0] = finalBaseRequestUrl[0] + "?";
            }
            String requestUrl = finalBaseRequestUrl[0] + "fbizdate=" + time;
            ResultRespVo everyDaySalary = getEveryDaySalary(finalToken, requestUrl);
            if (everyDaySalary != null && everyDaySalary.getRows().size() == 1) {
                salary[0] = salary[0].add(everyDaySalary.getRows().get(0).getFtamt());
            }
        });
        log.info("获取工资结果 => {}", salary[0]);
        return salary[0];
    }

    /**
     * 获取每天工资
     * 注
     * 1、通用的：个人或班组
     * 2、url中的self的值：（true时：个人工资，false时：班组工资）
     *
     * @param token 登录token
     * @param url   请求url
     * @return 获取结果
     */
    public ResultRespVo getEveryDaySalary(String token, String url) {
        String response = HttpRequest.get(url)
                .header("satoken", token)
                .execute()
                .body();
        log.info("获取工资响应结果 => {}", response);
        // 解析响应结果
        ResultRespVo resultRespVo = JSONObject.parseObject(response, ResultRespVo.class);
        return resultRespVo;
    }

    private List<String> getDateRangeList(String startTime, String endTime) {
        // 指定目标年月（示例为2025年5月）
        YearMonth targetMonth = YearMonth.of(2025, 2);
        // 获取当月所有日期
        return generateMonthDates(targetMonth);
    }

    /**
     * 生成指定年月的所有日期
     *
     * @param yearMonth 指定年月
     * @return 指定年月集合
     */
    public static List<String> generateMonthDates(YearMonth yearMonth) {
        List<String> dates = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // 获取当月第一天和最后一天
        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();

        // 遍历生成日期
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            dates.add(formatter.format(date));
        }
        return dates;
    }


    @Override
    public String test(User user) {
        return getToken(user);
    }

    /**
     * 获取token
     *
     * @param user 用户信息
     * @return 登录token
     */
    private String getToken(User user) {
        // 封装请求参数
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("object", JSONObject.toJSONString(user));
        requestParams.put("command", 10001);
        // 请求url
        String requestUrl = URL_PREFIX + LOGIN_URL;
        HttpResponse response = HttpRequest.post(requestUrl)
                .header("Accept", "application/json")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("User-Agent", "Mozilla/5.0 (Linux; Android 12; Pixel 6 Build/SP2A.220405.004)")
                .header("satoken", "")
                .form(requestParams)
                .execute();
        // 请求登录结果
        log.info("请求结果 => {}", response.body());
        // 获取token
        String token = JSONObject.parse(response.body()).getJSONObject("dataStore").getString("token");
        log.info("获取token的值 => {}", token);
        return token;
    }
}
