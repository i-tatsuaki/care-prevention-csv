package com.tatsuaki.carepreventioncsv.model.domain.csvcolumn

import java.util.regex.Pattern

class InsureNumber(insureNumber: String) : CsvColumn(insureNumber) {

    override fun getFormatErrorMessage(): String {
        val errorMessageBuilder = StringBuilder()

        if (!validateCharacter()) {
            errorMessageBuilder.append("数字0~9以外が含まれています.")
        }

        if (!validateLength()) {
            errorMessageBuilder.append("6桁ではありません.")
        }

        return errorMessageBuilder.toString()
    }

    override fun validate(): Boolean {
        return validateCharacter() && validateLength()
    }

    private fun validateCharacter(): Boolean {
        val regex = "[0-9]*"
        val p = Pattern.compile(regex)
        val m = p.matcher(content)
        return m.matches()
    }

    private fun validateLength(): Boolean {
        return content.length == LENGTH
    }

    companion object {
        private val LENGTH = 6
    }
}
