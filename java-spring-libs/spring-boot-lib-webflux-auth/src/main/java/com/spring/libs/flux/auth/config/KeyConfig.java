package com.spring.libs.flux.auth.config;

import com.spring.libs.flux.auth.core.entities.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@Configuration
@Slf4j
class KeyConfig {
    private static final String KEY_ALGORITHM = "RSA";
    private static final String KEY_PROVIDER = "BC";
    private final KeyFactory keyFactory;
    private final SecurityProperties securityProperties;

    @Autowired
    public KeyConfig(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
        Security.addProvider(new BouncyCastleProvider());
        try {
            keyFactory = KeyFactory.getInstance(KEY_ALGORITHM, KEY_PROVIDER);
        } catch (Exception e) {
            log.error("Error creating the key factory", e);
            throw new RuntimeException(e);
        }
    }

    @Bean
    @ConditionalOnMissingBean
    public PublicKey publicKey() {
        try {
            PemObject publicPem = readPemKey(securityProperties.getPublicKey());
            EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(publicPem.getContent());
            return keyFactory.generatePublic(pubKeySpec);
        } catch (Exception e) {
            log.error("Error generating public key, ", e);
            throw new RuntimeException(e);
        }
    }

    private PemObject readPemKey(String keyFilePath) {
        InputStream keyStream;
        PemObject pemObject;
        try {
            keyStream = getClass().getClassLoader().getResourceAsStream(keyFilePath);
            pemObject = new PemReader(new InputStreamReader(keyStream)).readPemObject();
        } catch (Exception e) {
            try {
                keyStream = new FileInputStream(keyFilePath);
                pemObject = new PemReader(new InputStreamReader(keyStream)).readPemObject();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }

        return pemObject;
    }
}
