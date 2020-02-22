package com.tatsuaki.carepreventioncsv.model.domain.csvcolumn

class ServiceKindCode(serviceKindCode: String) : CsvColumn(serviceKindCode) {

    override fun getFormatErrorMessage(): String {
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

    override fun getFormatContent(): String {
        return content
    }

    override fun validateCodeFormat(): Boolean {
        try {
            Code.valueOf(content)
            return true
        } catch (e: IllegalArgumentException) {
            return false
        }
    }
    override fun validateCharacter(): Boolean {
        return true
    }

    override fun validateLength(): Boolean {
        return true
    }
}
