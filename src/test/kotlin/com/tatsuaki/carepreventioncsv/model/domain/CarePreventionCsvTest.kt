package com.tatsuaki.carepreventioncsv.model.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.mock.web.MockMultipartFile
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

class CarePreventionCsvInitConfirmEncodeTest {

    companion object {
        val csvSample = "131136,A2,1111,201604,201909,訪問型独自サービス㈵　　　　　　　　　　　　　　　　　　　　　　,01168,03,,,3,,,,2,2,2,1,20190910"

        @JvmStatic
        fun parameters_init_confirmEncode() = listOf(
                arrayOf(Charset.forName("Shift_JIS"), "SHIFT_JIS"),
                arrayOf(StandardCharsets.UTF_8, "UTF-8")
        )
    }

    @ParameterizedTest
    @MethodSource("parameters_init_confirmEncode")
    fun `init_confirmEncode`(
            encodeType: Charset,
            encodeString: String
    ) {
        // given
        val file = MockMultipartFile(
                "data",
                "filename.txt",
                "text/plain",
                "131136,A2,1111,201604,201909,訪問型独自サービス㈵　　　　　　　　　　　　　　　　　　　　　　,01168,03,,,3,,,,2,2,2,1,20190910".toByteArray(encodeType)
        )

        // when
        val result = CarePreventionCsv(file)

        // then
        assertThat(result.encode).isEqualTo(encodeString)
    }

    @Test
    fun `init_confirmBom_BOMなし`() {
        // given
        val file = MockMultipartFile(
                "data",
                "filename.txt",
                "text/plain",
                csvSample.toByteArray()
        )

        // when
        val result = CarePreventionCsv(file)

        // then
        assertThat(result.isHasBom).isFalse()
    }

    @Test
    fun `init_confirmBom_BOMあり`() {
        // given
        val file = MockMultipartFile(
                "data",
                "filename.txt",
                "text/plain",
                byteArrayOf(0xEF.toByte(), 0xBB.toByte(), 0xBF.toByte()).plus(csvSample.toByteArray())
        )

        // when
        val result = CarePreventionCsv(file)

        // then
        assertThat(result.isHasBom).isTrue()
    }

    @Test
    fun `init_itemsに1件入る`() {
        // given
        val file = MockMultipartFile(
                "data",
                "filename.txt",
                "text/plain",
                csvSample.toByteArray()
        )

        // when
        val result = CarePreventionCsv(file)

        // then
        // 件数のみ確認する
        assertThat(result.getItems().size).isEqualTo(1)
    }

    @Test
    fun `init_itemsに2件以上入る`() {
        // given
        val file = MockMultipartFile(
                "data",
                "filename.txt",
                "text/plain",
                ("${csvSample}\n${csvSample}").toByteArray()
        )

        // when
        val result = CarePreventionCsv(file)

        // then
        // 件数のみ確認する
        assertThat(result.getItems().size).isEqualTo(2)
    }
}
