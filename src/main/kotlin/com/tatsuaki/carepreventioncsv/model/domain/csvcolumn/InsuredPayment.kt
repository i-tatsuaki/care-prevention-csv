package com.tatsuaki.carepreventioncsv.model.domain.csvcolumn

import java.util.regex.Pattern

class InsuredPayment(insuredPayment: String) : CsvColumn(insuredPayment) {

    override val regex = "[0-9]*"

    override fun getFormatErrorMessage(): String {
        val errorMessageBuilder = StringBuilder()

        if (!validateCharacter()) {
            errorMessageBuilder.append("数字0~9以外が含まれています.")
        }

        if (!validateLength()) {
            errorMessageBuilder.append("1~5桁ではありません.")
        }

        return errorMessageBuilder.toString()
    }

    override fun getFormatContent(): String {
        return content
    }

    override fun validateLength(): Boolean {
        return content.length <= LENGTH
    }

    override fun validateCodeFormat(): Boolean {
        return true
    }

    companion object {
        private val LENGTH = 5
    }

}
