package com.web.www.model.dto;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class IpAddressDto implements Serializable {

    private static final long serialVersionUID = -3892748923745L;

    // 大洲
    private String continent;

    // 国家
    private String country;

    private String zipcode;

    // 运营商
    private String owner;

    // 运营商
    private String isp;

    private String adcode;

    // 省份
    private String prov;

    // 城市
    private String city;

    // 区县
    private String district;

    public String toProvCityDistrictStr() {
        return prov + city + district;
    }

    public String toProvCityStr() {
        return prov + city;
    }

    public String toCountryProvCityDistrictStr() {
        return country + prov + city + district;
    }

    public String toCountryProvCityStr() {
        return country + prov + city;
    }

    public String toCountryProvStr() {
        return country + prov;
    }
}
