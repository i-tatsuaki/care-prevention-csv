package com.tatsuaki.carepreventioncsv.model.domain.CarePreventionCsvColumn

import java.util.regex.Matcher
import java.util.regex.Pattern

class ServiceCode(val serviceCode: String) {

    val formatErrorMessage: String
        get() {
            val errorMessageBuilder = StringBuilder()

            if (!validateCharcter()) {
                errorMessageBuilder.append("英数以外が含まれています.")
            }

            if (!validateLength()) {
                errorMessageBuilder.append("4桁ではありません.")
            }

            return errorMessageBuilder.toString()
        }

    private fun validateCharcter(): Boolean {
        val regex = "[0-9a-zA-Z]*"
        val p = Pattern.compile(regex)
        val m = p.matcher(serviceCode)
        return m.matches()
    }

    private fun validateLength(): Boolean {
        return serviceCode.length == LENGTH
    }

    companion object {
        private val LENGTH = 4
    }
}
