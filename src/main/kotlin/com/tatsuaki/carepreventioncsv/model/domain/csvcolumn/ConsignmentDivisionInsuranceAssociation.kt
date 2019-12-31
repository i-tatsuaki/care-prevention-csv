package com.tatsuaki.carepreventioncsv.model.domain.csvcolumn

import java.util.Arrays

import java.util.stream.Collectors.toList

/**
 * 事業対象者実施区分コード
 */
class ConsignmentDivisionInsuranceAssociation(private val consignmentDivisionInsuranceAssociation: String) {

    val formatErrorMessage: String
        get() {
            val errorMessageBuilder = StringBuilder()

            if (!validateCodeFormat()) {
                errorMessageBuilder.append("存在しない事業対象者実施区分コードです.")
            }

            return errorMessageBuilder.toString()
        }

    val description: String
        get() {
            return consignmentDivisionInsuranceAssociation + getJapanese(consignmentDivisionInsuranceAssociation)
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

    private fun validateCodeFormat(): Boolean {
        return Code.contains(consignmentDivisionInsuranceAssociation)
    }
}
