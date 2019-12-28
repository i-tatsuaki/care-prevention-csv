package com.tatsuaki.carepreventioncsv.model.domain.CarePreventionCsvColumn

class ServiceKindCode(val serviceKindCode: String) {

    val formatErrorMessage: String
        get() {
            val errorMessageBuilder = StringBuilder()

            if (!validateCodeFormat()) {
                errorMessageBuilder.append("存在しない総合事業サービス種類コードです.")
            }

            return errorMessageBuilder.toString()
        }

    enum class Code {
        A2,
        A3,
        A4,
        A6,
        A7,
        A8,
        A9,
        AA,
        AB,
        AC,
        AD,
        AE,
        AF
    }

    private fun validateCodeFormat(): Boolean {
        try {
            Code.valueOf(serviceKindCode)
            return true
        } catch (e: IllegalArgumentException) {
            return false
        }

    }
}
