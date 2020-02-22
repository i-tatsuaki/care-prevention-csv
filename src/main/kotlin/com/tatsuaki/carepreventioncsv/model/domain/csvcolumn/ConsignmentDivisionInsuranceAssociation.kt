package com.tatsuaki.carepreventioncsv.model.domain.csvcolumn

import java.util.Arrays

import java.util.stream.Collectors.toList

/**
 * 事業対象者実施区分コード
 */
class ConsignmentDivisionInsuranceAssociation(consignmentDivisionInsuranceAssociation: String) : CsvColumn(consignmentDivisionInsuranceAssociation) {

    override fun getFormatErrorMessage(): String {
        val errorMessageBuilder = StringBuilder()

        if (!validateCodeFormat()) {
            errorMessageBuilder.append("存在しない事業対象者実施区分コードです.")
        }

        return errorMessageBuilder.toString()
    }

    val description: String
        get() {
            return content + getJapanese(content)
        }

    private enum class Code constructor(val code: String, val japanese: String) {

        TARGET("1", "委託する"),
        NON_TARGET("2", "委託しない");

        companion object {

            fun contains(targetCode: String): Boolean {
                val targetCodes = Arrays.stream(values())
                        .filter { value -> value.code == targetCode }
                        .limit(1)
                        .collect(toList())

                return targetCodes.size != 0
            }

            fun of(targetCode: String): Code {
                val targetCodes = Arrays.stream(values())
                        .filter { value -> value.code == targetCode }
                        .limit(1)
                        .findFirst()
                        .get()

                return targetCodes
            }
        }
    }

    private fun getJapanese(consignmentDivisionInsuranceAssociation: String): String {
        return if (validateCodeFormat())
            "(" + Code.of(consignmentDivisionInsuranceAssociation).japanese + ")"
        else
            ""
    }

    override fun getFormatContent(): String {
        return content + getJapanese(content)
    }

    override fun validateCodeFormat(): Boolean {
        return Code.contains(content)
    }

    override fun validateCharacter(): Boolean {
        return true
    }

    override fun validateLength(): Boolean {
        return true
    }
}
