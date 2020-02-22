package com.tatsuaki.carepreventioncsv.model.domain.csvcolumn

import java.util.regex.Pattern

class UnitNumber(unitNumber: String) : CsvColumn(unitNumber) {

    override val regex = "[0-9]*|-[0-9]*"

    override fun getFormatErrorMessage(): String {
        val errorMessageBuilder = StringBuilder()

        if (!validateCharacter()) {
            errorMessageBuilder.append("単位数表現が誤っています.")
        }

        if (!validateLength()) {
            errorMessageBuilder.append("最大桁5桁を超えています.")
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
