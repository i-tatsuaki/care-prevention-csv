package com.tatsuaki.carepreventioncsv.model.domain

import org.springframework.web.multipart.MultipartFile

import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

enum class ByteOrderMark {

    UTF_8,
    UTF_16BE,
    UTF_16LE,
    UTF_32BE,
    UTF_32LE,
    NOT_BE;


    companion object {

        fun check(file: MultipartFile): ByteOrderMark {

            val BOM_SIZE = 4
            val bom = ByteArray(BOM_SIZE)

            val inputStream: InputStream
            try {
                inputStream = file.inputStream
                inputStream.read(bom, 0, bom.size)
            } catch (e: IOException) {
                System.err.println(e.message)
            }

            // Read ahead four bytes and check for BOM marks.
            return if (bom[0] == 0xEF.toByte() && bom[1] == 0xBB.toByte() && bom[2] == 0xBF.toByte()) {
                ByteOrderMark.UTF_8
            } else if (bom[0] == 0xFE.toByte() && bom[1] == 0xFF.toByte()) {
                ByteOrderMark.UTF_16BE
            } else if (bom[0] == 0xFF.toByte() && bom[1] == 0xFE.toByte()) {
                ByteOrderMark.UTF_16LE
            } else if (bom[0] == 0x00.toByte() && bom[1] == 0x00.toByte() && bom[2] == 0xFE.toByte() && bom[3] == 0xFF.toByte()) {
                ByteOrderMark.UTF_32BE
            } else if (bom[0] == 0xFF.toByte() && bom[1] == 0xFE.toByte() && bom[2] == 0x00.toByte() && bom[3] == 0x00.toByte()) {
                UTF_32LE
            } else {
                NOT_BE
            }
        }
    }
}
