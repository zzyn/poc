package com.spring.libs.jwt.config;

public class KeyConfig {

    private String privateKey;

    private String publicKey;

    public KeyConfig(String privateKey, String publicKey) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }
}
