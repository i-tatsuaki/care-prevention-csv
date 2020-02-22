package com.tatsuaki.carepreventioncsv.model.domain.csvcolumn

import java.util.regex.Pattern

class CreatedDate(createdDate: String) : CsvColumn(createdDate) {

    override fun getFormatErrorMessage(): String {
        val errorMessageBuilder = StringBuilder()

        if (!validateCharacter()) {
            errorMessageBuilder.append("数字0~9以外が含まれています.")
        }

        if (!validateLength()) {
            errorMessageBuilder.append("8桁ではありません.")
        }

        return errorMessageBuilder.toString()
    }

    override fun validateCharacter(): Boolean {
        val regex = "[0-9]*"
        val p = Pattern.compile(regex)
        val m = p.matcher(content)
        return m.matches()
    }

    override fun validateLength(): Boolean {
        return content.length == LENGTH
    }

    override fun validateCodeFormat(): Boolean {
        return true
    }

    companion object {
        private val LENGTH = 8
    }

}
