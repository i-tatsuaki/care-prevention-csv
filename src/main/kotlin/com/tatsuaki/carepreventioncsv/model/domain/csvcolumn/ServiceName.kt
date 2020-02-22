package com.tatsuaki.carepreventioncsv.model.domain.csvcolumn

class ServiceName(serviceName: String) : CsvColumn(serviceName) {

    override fun getFormatErrorMessage(): String {
        val errorMessageBuilder = StringBuilder()

        if (!validateLength()) {
            errorMessageBuilder.append("文字数が64を超えています.")
        }

        return errorMessageBuilder.toString()
    }

    override fun getFormatContent(): String {
        return content
    }

    override fun validateLength(): Boolean {
        return content.length <= LENGTH
    }

    override fun validateCharacter(): Boolean {
        return true
    }

    override fun validateCodeFormat(): Boolean {
        return true
    }

    companion object {
        private val LENGTH = 64
    }
}
