package com.web.www.config;

import cn.dev33.satoken.strategy.SaStrategy;
import cn.dev33.satoken.util.SaFoxUtil;
import cn.hutool.core.codec.Base64Encoder;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class SaTokenConfigure {

    /**
     * 重写 Sa-Token 框架内部算法策略
     */
    @PostConstruct
    public void rewriteSaStrategy() {
        // 重写 Token 生成策略，自定义token生成规则
        SaStrategy.instance.createToken = (loginId, loginType) -> {
            return Base64Encoder.encode(SaFoxUtil.getRandomString(64));    // 随机60位长度字符串
        };
    }

}
