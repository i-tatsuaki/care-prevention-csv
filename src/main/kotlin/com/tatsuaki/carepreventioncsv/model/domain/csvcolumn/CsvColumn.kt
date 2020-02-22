package com.tatsuaki.carepreventioncsv.model.domain.csvcolumn

abstract class CsvColumn(val content: String) {

    val isError: Boolean
        get() {
            return validate()
        }

    abstract fun getFormatErrorMessage(): String
    abstract fun validate(): Boolean
}