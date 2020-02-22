package com.tatsuaki.carepreventioncsv.model.domain.csvcolumn

import java.util.regex.Pattern

abstract class CsvColumn(val content: String) {

    open val regex: String = ".*"

    val isError: Boolean
        get() {
            return validate()
        }

    fun validate(): Boolean {
        return validateCharacter() && validateLength() && validateCodeFormat()
    }

    abstract fun getFormatContent(): String
    abstract fun getFormatErrorMessage(): String
    fun validateCharacter(): Boolean {
        val p = Pattern.compile(regex)
        val m = p.matcher(content)
        return m.matches()
    }
    abstract fun validateLength(): Boolean
    abstract fun validateCodeFormat(): Boolean
}