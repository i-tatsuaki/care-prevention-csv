package com.tatsuaki.carepreventioncsv.model.domain.csvcolumn

import java.util.Arrays

import java.util.stream.Collectors.toList

class ImplementationDivisionForSupportNeed1(implementationDivisionForSupportNeed1: String) : CsvColumn(implementationDivisionForSupportNeed1) {

    override fun getFormatErrorMessage(): String {
        val errorMessageBuilder = StringBuilder()

        if (!validateCodeFormat()) {
            errorMessageBuilder.append("存在しない要支援１受給者実施区分コードです.")
        }

        return errorMessageBuilder.toString()
    }


    enum class Code private constructor(val code: String, val japanese: String) {

        TARGET("1", "実施不可"),
        NON_TARGET("2", "実施可");


        companion object {

            operator fun contains(targetCode: String): Boolean {
                val targetCodes = Arrays.stream(values())
                        .filter { value -> value.code == targetCode }
                        .limit(1)
                        .collect(toList())

                return targetCodes.size != 0
            }

            fun getJapanese(targetCode: String): String {
                val targetCodes = Arrays.stream(values())
                        .filter { value -> value.code == targetCode }
                        .limit(1)
                        .collect(toList())

                return if (targetCodes.size == 0) {
                    ""
                } else targetCodes[0].japanese

            }
        }
    }

    private fun getJapanese(implementationDivisionForSupportNeed1: String): String {
        return if (validateCodeFormat())
            "(" + ImplementationDivisionForSupportNeed1.Code.getJapanese(implementationDivisionForSupportNeed1) + ")"
        else
            ""
    }

    override fun getFormatContent(): String {
        return content + getJapanese(content)
    }

    override fun validateCodeFormat(): Boolean {
        return ImplementationDivisionForSupportNeed1.Code.contains(content)
    }

    override fun validateLength(): Boolean {
        return true
    }
}
