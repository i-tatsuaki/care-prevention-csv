package com.tatsuaki.carepreventioncsv.model.domain.csvcolumn

class SpareColumn(spareColumn: String) : CsvColumn(spareColumn) {

    override fun getFormatErrorMessage(): String {
        // フリーテキストのためチェックなし
        return ""
    }

    override fun validateCharacter(): Boolean {
        return true
    }

    override fun validateLength(): Boolean {
        return true
    }

    override fun validateCodeFormat(): Boolean {
        return true
    }
}
