package com.tatsuaki.carepreventioncsv.model.domain.CarePreventionCsvColumn

import java.util.regex.Matcher
import java.util.regex.Pattern

class EndDate(val endDate: String) {

    val formatErrorMessage: String
        get() {
            val errorMessageBuilder = StringBuilder()

            if (!validateCharcter()) {
                errorMessageBuilder.append("数字0~9以外が含まれています.")
            }

            if (!validateLength()) {
                errorMessageBuilder.append("6桁ではありません.")
            }

            return errorMessageBuilder.toString()
        }

    private fun validateCharcter(): Boolean {
        val regex = "[0-9]*"
        val p = Pattern.compile(regex)
        val m = p.matcher(endDate)
        return m.matches()
    }

    private fun validateLength(): Boolean {
        return endDate.length == LENGTH
    }

    companion object {
        private val LENGTH = 6
    }

}
