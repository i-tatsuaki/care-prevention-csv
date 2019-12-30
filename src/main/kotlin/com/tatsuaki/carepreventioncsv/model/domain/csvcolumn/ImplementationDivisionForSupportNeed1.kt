package com.tatsuaki.carepreventioncsv.model.domain.csvcolumn

import java.util.Arrays

import java.util.stream.Collectors.toList

class ImplementationDivisionForSupportNeed1(private val implementationDivisionForSupportNeed1: String) {

    val formatErrorMessage: String
        get() {
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

    fun getImplementationDivisionForSupportNeed1(): String {
        return implementationDivisionForSupportNeed1 + getJapanese(implementationDivisionForSupportNeed1)
    }

    private fun getJapanese(implementationDivisionForSupportNeed1: String): String {
        return if (validateCodeFormat())
            "(" + ImplementationDivisionForSupportNeed1.Code.getJapanese(implementationDivisionForSupportNeed1) + ")"
        else
            ""
    }

    private fun validateCodeFormat(): Boolean {
        return ImplementationDivisionForSupportNeed1.Code.contains(implementationDivisionForSupportNeed1)
    }
}
