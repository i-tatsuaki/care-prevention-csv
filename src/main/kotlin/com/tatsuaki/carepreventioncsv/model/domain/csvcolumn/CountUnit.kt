package com.tatsuaki.carepreventioncsv.model.domain.csvcolumn

import java.util.Arrays

import java.util.stream.Collectors.toList

class CountUnit(countUnit: String) : CsvColumn(countUnit) {

    override fun getFormatErrorMessage(): String {
        val errorMessageBuilder = StringBuilder()

        if (!validateCodeFormat()) {
            errorMessageBuilder.append("存在しない算定単位コードです.")
        }

        return errorMessageBuilder.toString()
    }

    enum class Code private constructor(val code: String, val japanese: String) {

        TIMES("01", "回数単位"),
        DAILY("02", "日単位"),
        MONTHLY("03", "月単位"),
        WEEKLY("05", "週単位");


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

    private fun getJapanese(countUnit: String): String {
        return if (validateCodeFormat())
            "(" + Code.getJapanese(countUnit) + ")"
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
