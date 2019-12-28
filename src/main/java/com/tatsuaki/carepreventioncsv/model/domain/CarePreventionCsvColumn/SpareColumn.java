package com.tatsuaki.carepreventioncsv.model.domain.CarePreventionCsvColumn;

public class SpareColumn {

    private final String spareColumn;

    public SpareColumn(String spareColumn) {
        this.spareColumn = spareColumn;
    }

    public String getSpareColumn() {
        return spareColumn;
    }

    public String getFormatErrorMessage() {
        // フリーテキストのためチェックなし
        return "";
    }
}
