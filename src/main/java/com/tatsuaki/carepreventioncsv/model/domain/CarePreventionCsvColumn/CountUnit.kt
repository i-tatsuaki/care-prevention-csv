package com.tatsuaki.carepreventioncsv.model.domain.CarePreventionCsvColumn

import java.util.Arrays

import java.util.stream.Collectors.toList

class CountUnit(private val countUnit: String) {

    val formatErrorMessage: String
        get() {
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

    fun getCountUnit(): String {
        return countUnit + getJapanese(countUnit)
    }

    private fun getJapanese(countUnit: String): String {
        return if (validateCodeFormat())
            "(" + Code.getJapanese(countUnit) + ")"
        else
            ""
    }

    private fun validateCodeFormat(): Boolean {
        return Code.contains(countUnit)
    }
}
