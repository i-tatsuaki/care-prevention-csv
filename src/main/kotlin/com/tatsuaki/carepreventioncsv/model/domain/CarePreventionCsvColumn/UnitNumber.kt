package com.tatsuaki.carepreventioncsv.model.domain.CarePreventionCsvColumn

import java.util.regex.Matcher
import java.util.regex.Pattern

class UnitNumber(val unitNumber: String) {

    val formatErrorMessage: String
        get() {
            val errorMessageBuilder = StringBuilder()

            if (!validateCharcter()) {
                errorMessageBuilder.append("単位数表現が誤っています.")
            }

            if (!validateLength()) {
                errorMessageBuilder.append("最大桁5桁を超えています.")
            }

            return errorMessageBuilder.toString()
        }

    private fun validateCharcter(): Boolean {
        val regex = "[0-9]*|-[0-9]*"
        val p = Pattern.compile(regex)
        val m = p.matcher(unitNumber)
        return m.matches()
    }

    private fun validateLength(): Boolean {
        return unitNumber.length <= LENGTH
    }

    companion object {
        private val LENGTH = 5
    }

}
