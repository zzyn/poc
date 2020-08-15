package com.webflux.cassandra.demo.core.jwt;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Objects;

public class KeyUtils {

    private static final String ALGORITHM = "RSA";
    private static final String KEY_PROVIDER = "BC";
    private static BouncyCastleProvider bouncyCastleProvider = null;

    public KeyUtils() {
    }

    private static synchronized BouncyCastleProvider getInstance() {
        if (bouncyCastleProvider == null) {
            bouncyCastleProvider = new BouncyCastleProvider();
        }
        return bouncyCastleProvider;
    }

    private static PrivateKey generatePrivateKey(KeyFactory factory, String filename) throws InvalidKeySpecException, FileNotFoundException, IOException {
        PemFile pemFile = new PemFile(filename);
        byte[] content = pemFile.getPemObject().getContent();
        PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(content);
        return factory.generatePrivate(privKeySpec);
    }

    private static PrivateKey generatePrivateKey(KeyFactory factory, InputStream inputStream) throws IOException, InvalidKeySpecException {
        PemFile pemFile = new PemFile(inputStream);
        byte[] content = pemFile.getPemObject().getContent();
        PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(content);
        return factory.generatePrivate(privKeySpec);
    }

    private static PublicKey generatePublicKey(KeyFactory factory, String filename) throws InvalidKeySpecException, IOException {
        PemFile pemFile = new PemFile(filename);
        byte[] content = pemFile.getPemObject().getContent();
        X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(content);
        return factory.generatePublic(pubKeySpec);
    }

    private static PublicKey generatePublicKey(KeyFactory factory, InputStream inputStream) throws InvalidKeySpecException, IOException {
        PemFile pemFile = new PemFile(inputStream);
        byte[] content = pemFile.getPemObject().getContent();
        X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(content);
        return factory.generatePublic(pubKeySpec);
    }

    private static PrivateKey getPrivateKey(String filename) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException, IOException {

        if (Objects.isNull(Security.getProvider(KEY_PROVIDER))) {
            Security.addProvider(getInstance());
        }

        KeyFactory factory = KeyFactory.getInstance(ALGORITHM, KEY_PROVIDER);

        return generatePrivateKey(factory, filename);
    }

    public static PrivateKey getPrivateKey(InputStream inputStream) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException, IOException {

        if (Objects.isNull(Security.getProvider(KEY_PROVIDER))) {
            Security.addProvider(getInstance());
        }

        KeyFactory factory = KeyFactory.getInstance(ALGORITHM, KEY_PROVIDER);

        return generatePrivateKey(factory, inputStream);
    }


    private static PublicKey getPublicKey(String filename) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException, IOException {

        if (Objects.isNull(Security.getProvider(KEY_PROVIDER))) {
            Security.addProvider(getInstance());
        }

        KeyFactory factory = KeyFactory.getInstance(ALGORITHM, KEY_PROVIDER);

        return generatePublicKey(factory, filename);

    }

    public static PublicKey getPublicKey(InputStream inputStream) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException, IOException {

        if (Objects.isNull(Security.getProvider(KEY_PROVIDER))) {
            Security.addProvider(getInstance());
        }

        KeyFactory factory = KeyFactory.getInstance(ALGORITHM, KEY_PROVIDER);

        return generatePublicKey(factory, inputStream);
    }
}
