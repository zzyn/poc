package com.spring.libs.jwt.utils;

import com.spring.libs.jwt.constants.JwtConstants;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Objects;

public class KeyUtils {

    private static BouncyCastleProvider bouncyCastleProvider = null;

    public KeyUtils() {
    }

    private static synchronized BouncyCastleProvider getInstance() {
        if (Objects.isNull(bouncyCastleProvider)) {
            bouncyCastleProvider = new BouncyCastleProvider();
        }
        return bouncyCastleProvider;
    }

    private static PrivateKey generatePrivateKey(KeyFactory factory, String filename) throws InvalidKeySpecException, IOException {
        return factory.generatePrivate(new PKCS8EncodedKeySpec(new PemFileUtils(filename).getPemObject().getContent()));
    }

    private static PrivateKey generatePrivateKey(KeyFactory factory, InputStream inputStream) throws IOException, InvalidKeySpecException {
        return factory.generatePrivate(new PKCS8EncodedKeySpec(new PemFileUtils(inputStream).getPemObject().getContent()));
    }

    private static PublicKey generatePublicKey(KeyFactory factory, String filename) throws InvalidKeySpecException, IOException {
        return factory.generatePublic(new X509EncodedKeySpec(new PemFileUtils(filename).getPemObject().getContent()));
    }

    private static PublicKey generatePublicKey(KeyFactory factory, InputStream inputStream) throws InvalidKeySpecException, IOException {
        return factory.generatePublic(new X509EncodedKeySpec(new PemFileUtils(inputStream).getPemObject().getContent()));
    }

    private static PrivateKey getPrivateKey(String filename) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        if (Objects.isNull(Security.getProvider(JwtConstants.KEY_FACTORY_PROVIDER))) {
            Security.addProvider(getInstance());
        }
        return generatePrivateKey(KeyFactory.getInstance(JwtConstants.KEY_FACTORY_ALGORITHM, JwtConstants.KEY_FACTORY_PROVIDER), filename);
    }

    public static PrivateKey getPrivateKey(InputStream inputStream) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        if (Objects.isNull(Security.getProvider(JwtConstants.KEY_FACTORY_PROVIDER))) {
            Security.addProvider(getInstance());
        }
        return generatePrivateKey(KeyFactory.getInstance(JwtConstants.KEY_FACTORY_ALGORITHM, JwtConstants.KEY_FACTORY_PROVIDER), inputStream);
    }

    private static PublicKey getPublicKey(String filename) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        if (Objects.isNull(Security.getProvider(JwtConstants.KEY_FACTORY_PROVIDER))) {
            Security.addProvider(getInstance());
        }
        return generatePublicKey(KeyFactory.getInstance(JwtConstants.KEY_FACTORY_ALGORITHM, JwtConstants.KEY_FACTORY_PROVIDER), filename);
    }

    public static PublicKey getPublicKey(InputStream inputStream) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        if (Objects.isNull(Security.getProvider(JwtConstants.KEY_FACTORY_PROVIDER))) {
            Security.addProvider(getInstance());
        }
        return generatePublicKey(KeyFactory.getInstance(JwtConstants.KEY_FACTORY_ALGORITHM, JwtConstants.KEY_FACTORY_PROVIDER), inputStream);
    }
}
