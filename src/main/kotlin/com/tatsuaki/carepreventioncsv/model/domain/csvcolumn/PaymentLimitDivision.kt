package com.tatsuaki.carepreventioncsv.model.domain.csvcolumn

import java.util.Arrays

import java.util.stream.Collectors.toList

class PaymentLimitDivision(paymentLimitDivision: String) : CsvColumn(paymentLimitDivision) {

    override fun getFormatErrorMessage(): String {
        val errorMessageBuilder = StringBuilder()

        if (!validateCodeFormat()) {
            errorMessageBuilder.append("存在しない支給限度額対象区分コードです.")
        }

        return errorMessageBuilder.toString()
    }

    enum class Code private constructor(val code: String, val japanese: String) {

        TARGET("3", "対象");


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

    private fun getJapanese(paymentLimitDivision: String): String {
        return if (validateCodeFormat() && !paymentLimitDivision.isEmpty())
            "(" + PaymentLimitDivision.Code.getJapanese(paymentLimitDivision) + ")"
        else
            ""
    }

    override fun getFormatContent(): String {
        return content + getJapanese(content)
    }

    override fun validateCodeFormat(): Boolean {
        return content.isEmpty() || PaymentLimitDivision.Code.contains(content)
    }

    override fun validateCharacter(): Boolean {
        return true
    }

    override fun validateLength(): Boolean {
        return true
    }
}
