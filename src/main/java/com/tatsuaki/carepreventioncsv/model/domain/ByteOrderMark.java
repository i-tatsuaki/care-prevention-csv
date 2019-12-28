package com.tatsuaki.carepreventioncsv.model.domain;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public enum ByteOrderMark {

    UTF_8,
    UTF_16BE,
    UTF_16LE,
    UTF_32BE,
    UTF_32LE,
    NOT_BE;

    public static final ByteOrderMark check(MultipartFile file) {

        final int BOM_SIZE = 4;
        byte bom[] = new byte[BOM_SIZE];

        InputStream inputStream;
        try {
            inputStream = file.getInputStream();
            inputStream.read(bom, 0, bom.length);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        // Read ahead four bytes and check for BOM marks.
        if ((bom[0] == (byte) 0xEF) && (bom[1] == (byte) 0xBB) && (bom[2] == (byte) 0xBF)) {
            return ByteOrderMark.UTF_8;
        } else if ((bom[0] == (byte) 0xFE) && (bom[1] == (byte) 0xFF)) {
            return ByteOrderMark.UTF_16BE;
        } else if ((bom[0] == (byte) 0xFF) && (bom[1] == (byte) 0xFE)) {
            return ByteOrderMark.UTF_16LE;
        } else if ((bom[0] == (byte) 0x00) && (bom[1] == (byte) 0x00) && (bom[2] == (byte) 0xFE) && (bom[3] == (byte) 0xFF)) {
            return ByteOrderMark.UTF_32BE;
        } else if ((bom[0] == (byte) 0xFF) && (bom[1] == (byte) 0xFE) && (bom[2] == (byte) 0x00) && (bom[3] == (byte) 0x00)) {
            return UTF_32LE;
        } else {
            return NOT_BE;
        }
    }
}
