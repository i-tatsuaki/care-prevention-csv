package com.tatsuaki.carepreventioncsv.model.domain.csvcolumn

import java.util.Arrays

import java.util.stream.Collectors.toList

class TermLimitCount(termLimitCount: String) : CsvColumn(termLimitCount) {

    override fun getFormatErrorMessage(): String {
        val errorMessageBuilder = StringBuilder()

        if (!validateCodeFormat()) {
            errorMessageBuilder.append("存在しない算定回数制限期間コードです.")
        }

        return errorMessageBuilder.toString()
    }

    enum class Code private constructor(val code: String, val japanese: String) {

        MONTHLY("01", "月単位"),
        DAILY("08", "日単位"),
        WEEKLY("16", "週単位");


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

    fun getTermLimitCount(): String {
        return content + getJapanese(content)
    }

    private fun getJapanese(termLimitCount: String): String {
        return if (validateCodeFormat() && !termLimitCount.isEmpty())
            "(" + TermLimitCount.Code.getJapanese(termLimitCount) + ")"
        else
            ""
    }

    override fun validateCodeFormat(): Boolean {
        return content.isEmpty() || TermLimitCount.Code.contains(content)
    }

    override fun validateCharacter(): Boolean {
        return true
    }

    override fun validateLength(): Boolean {
        return true
    }
}
