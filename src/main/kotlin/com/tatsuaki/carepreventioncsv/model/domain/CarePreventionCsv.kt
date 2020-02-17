package com.tatsuaki.carepreventioncsv.model.domain

import com.opencsv.CSVReader
import org.mozilla.universalchardet.UniversalDetector
import org.springframework.web.multipart.MultipartFile

import java.io.IOException
import java.nio.charset.Charset
import java.util.ArrayList
import java.util.Arrays

class CarePreventionCsv(file: MultipartFile) {

    private val items: MutableList<CarePreventionCsvItem>
    var encode: String? = null
        private set
    var isHasBom: Boolean = false
        private set
    private var byteOrderMark: ByteOrderMark? = null

    init {
        this.items = ArrayList()

        confirmEncode(file)
        confirmBom(file)

        try {
            CSVReader(file.inputStream.bufferedReader(Charset.forName("SJIS"))).use { csvReader ->
                csvReader.forEach {
                    items.add(CarePreventionCsvItem.CreateFromCsvLine(it))
                }
            }
        } catch (e: IOException) {
            System.err.println(e.message)
        }

    }

    private fun confirmBom(file: MultipartFile) {
        this.byteOrderMark = ByteOrderMark.check(file)
        isHasBom = this.byteOrderMark != ByteOrderMark.NOT_BE
    }

    private fun confirmEncode(file: MultipartFile) {
        val detector = UniversalDetector(null)

        try {
            val buffer = ByteArray(4096)

            val inputStream = file.inputStream

            while (true) {
                var nread = inputStream.read(buffer)
                if (nread > 0 && !detector.isDone()) {
                    detector.handleData(buffer, 0, nread);
                } else {
                    break
                }
            }
            detector.dataEnd()

            this.encode = detector.detectedCharset

        } catch (e: IOException) {
            System.err.println(e.message)
        }

    }

    fun getItems(): List<CarePreventionCsvItem> {
        return items
    }

    companion object {

        val HEADER = Arrays.asList(
                "証記載保険者番号",
                "サービス種類コード",
                "サービス項目コード",
                "適用開始年月",
                "適用終了年月",
                "サービス名称",
                "単位数",
                "算定単位",
                "制限日数・回数",
                "算定回数制限期間",
                "支給限度額対象区分",
                "予備項目",
                "給付率",
                "利用者負担額",
                "事業対象者実施区分",
                "要支援１受給者実施区分",
                "要支援２受給者実施区分",
                "国保連合会委託区分",
                "作成年月日"
        )
    }

    fun getHEADER() = HEADER
}
