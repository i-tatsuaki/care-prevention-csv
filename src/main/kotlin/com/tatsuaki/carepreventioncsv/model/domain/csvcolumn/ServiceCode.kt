package com.tatsuaki.carepreventioncsv.model.domain.csvcolumn

import java.util.regex.Pattern

class ServiceCode(serviceCode: String) : CsvColumn(serviceCode) {

    override val regex = "[0-9a-zA-Z]*"

    override fun getFormatErrorMessage(): String {
        val errorMessageBuilder = StringBuilder()

        if (!validateCharacter()) {
            errorMessageBuilder.append("英数以外が含まれています.")
        }

        if (!validateLength()) {
            errorMessageBuilder.append("4桁ではありません.")
        }

        return errorMessageBuilder.toString()
    }

    override fun getFormatContent(): String {
        return content
    }

    override fun validateLength(): Boolean {
        return content.length == LENGTH
    }

    override fun validateCodeFormat(): Boolean {
        return true
    }

    companion object {
        private val LENGTH = 4
    }
}
