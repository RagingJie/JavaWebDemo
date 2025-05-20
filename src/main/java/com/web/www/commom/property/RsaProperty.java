package com.web.www.commom.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * RSA配置
 *
 * @author naruto
 * @since 2025/5/20 17:07
 */
@Component
public class RsaProperty {

    // 公钥
    public static String publicKey;

    // 私钥
    public static String privateKey;

    @Value("${rsa.public-key}")
    public void setPublicKey(String publicKey) {
        RsaProperty.publicKey = publicKey;
    }

    @Value("${rsa.private-key}")
    public void setPrivateKey(String privateKey) {
        RsaProperty.privateKey = privateKey;
    }
}
