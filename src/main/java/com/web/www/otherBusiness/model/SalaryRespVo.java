package com.web.www.otherBusiness.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SalaryRespVo {

    // 单据号
    private String fid;
    // 单据编号
    private String fnumber;
    // 业务日期
    private String fbizdate;
    // 公司编码
    private String fcompanyid;
    // 业务员编码
    private String fpersonid;
    // 操作员编码
    private String fuserid;
    // 单据状态
    private String fstatus;
    // 父级单据号
    private String fparentid;
    // 来源单据号
    private String fsourceid;
    // 控制编号
    private String fcontrolid;
    // 删除标志
    private String fdeleteflag;
    // 创建人编码
    private String fcreateuserid;
    // 创建人名称
    private String fcreateusername;
    // 创建时间
    private String fcreatetime;
    // 最后修改人编码
    private String flastupdateuserid;
    // 最后修改人名称
    private String flastupdateusername;
    // 最后修改时间
    private String flastupdatetime;
    // 业务对象类型
    private String bosType;
    // 操作类型
    private String doWhat;
    // 请求地址
    private String url;
    // 提示信息
    private String valimsg;
    // 岗位编码
    private String fpostionid;
    // 工人姓名
    private String fname;
    // 岗位名称
    private String fpname;
    // 岗位类型（班组）
    private String ftname;
    // 计数器编码
    private String fcounterid;
    // 岗位类别名称
    private String fcname;
    // 工作时间，天，本日出勤
    private Double fwtime;
    // 工资系数
    private String fpratio;
    // 数量1
    private String fqty_1;
    // 数量2
    private String fqty_2;
    // 数量3
    private String fqty_3;
    // 数量4
    private String fqty_4;
    // 数量5
    private String fqty_5;
    // 数量6
    private String fqty_6;
    // 金额
    private String fjamt;
    // 值1
    private String fvalue_1;
    // 值2
    private String fvalue_2;
    // 值3
    private String fvalue_3;
    // 值4
    private String fvalue_4;
    // 值5
    private String fvalue_5;
    // 值6
    private String fvalue_6;
    // 总金额
    private String ftvalue;
    // 金额2
    private String famt_2;
    // 金额3
    private String famt_3;
    // 本日工资
    private BigDecimal ftamt;
    // 部门编码
    private String fteamid;
    // 工资金额
    private String fsalaryamt;
    // 工资 Formula金额
    private String fformulaamt;
    // 奖金级别
    private Integer jb;
    // 奖金数量
    private Integer qt;
    // 奖金金额
    private String fkqfkamt;
    // 奖金金额
    private String fqtgdamt;
    // 奖金金额
    private String fxgbz;
    // 奖金金额
    private String fsubsidy;
    // 奖金金额
    private String fxsamt;
}