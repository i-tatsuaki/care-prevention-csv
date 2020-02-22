package com.tatsuaki.carepreventioncsv.model.domain.csvcolumn

abstract class CsvColumn(val content: String) {

    val isError: Boolean
        get() {
            return validate()
        }

    fun validate(): Boolean {
        return validateCharacter() && validateLength() && validateCodeFormat()
    }

    abstract fun getFormatErrorMessage(): String
    abstract fun validateCharacter(): Boolean
    abstract fun validateLength(): Boolean
    abstract fun validateCodeFormat(): Boolean
}