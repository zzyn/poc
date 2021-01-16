package com.spring.libs.jwt.config;

import com.spring.libs.jwt.utils.KeyUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

import java.security.PrivateKey;
import java.security.PublicKey;

@Configuration
public class JwtAutoConfiguration {

    @Value("${jwt.private-key:keys/rsa/private_key_default.pem}")
    private String privateKey;

    @Value("${jwt.public-key:keys/rsa/public_key_default.pem}")
    private String publicKey;

    @Bean
    public KeyConfig getKeyConfig() {
        return new KeyConfig(this.privateKey, this.publicKey);
    }

    @Bean
    public PrivateKey getPrivateKey(ResourceLoader resourceLoader, KeyConfig keyConfig) throws Exception {
        return KeyUtils.getPrivateKey(resourceLoader.getClassLoader().getResourceAsStream(keyConfig.getPrivateKey()));
    }

    @Bean
    public PublicKey getPublicKey(ResourceLoader resourceLoader, KeyConfig keyConfig) throws Exception {
        return KeyUtils.getPublicKey(resourceLoader.getClassLoader().getResourceAsStream(keyConfig.getPublicKey()));
    }
}
