package com.tatsuaki.carepreventioncsv.model.domain.csvcolumn

class ServiceKindCode(serviceKindCode: String) : CsvColumn(serviceKindCode) {

    override fun getFormatErrorMessage(): String {
        val errorMessageBuilder = StringBuilder()

        if (!validateCodeFormat()) {
            errorMessageBuilder.append("存在しない総合事業サービス種類コードです.")
        }

        return errorMessageBuilder.toString()
    }

    override fun validate(): Boolean {
        return validateCodeFormat()
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
            Code.valueOf(content)
            return true
        } catch (e: IllegalArgumentException) {
            return false
        }

    }
}
