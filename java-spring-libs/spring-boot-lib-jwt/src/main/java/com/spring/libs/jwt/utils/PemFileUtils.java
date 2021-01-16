package com.spring.libs.jwt.utils;

import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.bouncycastle.util.io.pem.PemWriter;

import java.io.*;

public class PemFileUtils {

    private PemObject pemObject;

    public PemFileUtils(String filename) throws IOException {
        try (PemReader pemReader = new PemReader(new InputStreamReader(new FileInputStream(filename)))) {
            this.pemObject = pemReader.readPemObject();
        }
    }

    public PemFileUtils(InputStream inputStream) throws IOException {
        try (PemReader pemReader = new PemReader(new InputStreamReader(inputStream))) {
            this.pemObject = pemReader.readPemObject();
        }
    }

    public void write(String filename) throws IOException {
        try (PemWriter pemWriter = new PemWriter(new OutputStreamWriter(new FileOutputStream(filename)))) {
            pemWriter.writeObject(this.pemObject);
        }
    }

    public void write(OutputStream outputStream) throws IOException {
        try (PemWriter pemWriter = new PemWriter(new OutputStreamWriter(outputStream))) {
            pemWriter.writeObject(this.pemObject);
        }
    }

    public PemObject getPemObject() {
        return pemObject;
    }

}

