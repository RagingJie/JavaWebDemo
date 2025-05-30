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
    private static String continent;

    // 国家
    private static String country;

    private String zipcode;

    // 运营商
    private String owner;

    // 运营商
    private String isp;

    private String adcode;

    // 省份
    private static String prov;

    // 城市
    private static String city;

    // 区县
    private static String district;

    public static String toProvCityDistrictStr() {
        return prov + city + district;
    }

    public static String toProvCityStr() {
        return prov + city;
    }

    public static String toCountryProvCityDistrictStr() {
        return country + prov + city + district;
    }

    public static String toCountryProvCityStr() {
        return country + prov + city;
    }

    public static String toCountryProvStr() {
        return country + prov;
    }
}
