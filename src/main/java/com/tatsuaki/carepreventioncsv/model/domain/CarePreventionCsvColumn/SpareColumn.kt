package com.tatsuaki.carepreventioncsv.model.domain.CarePreventionCsvColumn

class SpareColumn(val spareColumn: String) {

    // フリーテキストのためチェックなし
    val formatErrorMessage: String
        get() = ""
}
