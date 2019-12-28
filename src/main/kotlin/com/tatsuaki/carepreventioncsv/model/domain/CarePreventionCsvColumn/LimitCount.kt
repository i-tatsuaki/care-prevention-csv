package com.tatsuaki.carepreventioncsv.model.domain.CarePreventionCsvColumn

import java.util.Arrays
import java.util.regex.Matcher
import java.util.regex.Pattern

import java.util.stream.Collectors.toList

class LimitCount(val limitCount: String) {

    val formatErrorMessage: String
        get() {
            val errorMessageBuilder = StringBuilder()

            if (!validateCharcter()) {
                errorMessageBuilder.append("数字0~9以外が含まれています.")
            }

            if (!validateLength()) {
                errorMessageBuilder.append("1~2桁ではありません.")
            }

            return errorMessageBuilder.toString()
        }

    private fun validateCharcter(): Boolean {
        val regex = "[0-9]*"
        val p = Pattern.compile(regex)
        val m = p.matcher(limitCount)
        return m.matches()
    }

    private fun validateLength(): Boolean {
        return limitCount.isEmpty() || limitCount.length == LENGTH
    }

    companion object {
        private val LENGTH = 2
    }

}
