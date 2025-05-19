package com.web.www.otherBusiness.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ResultRespVo {

    // 总记录数
    private Integer totalRows;
    // 总页数
    private Integer totalPages;
    // 数据
    private List<SalaryRespVo> rows;
}
