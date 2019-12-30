package com.tatsuaki.carepreventioncsv.model.domain.csvcolumn

class SpareColumn(val spareColumn: String) {

    // フリーテキストのためチェックなし
    val formatErrorMessage: String
        get() = ""
}
