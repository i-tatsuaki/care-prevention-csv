package com.tatsuaki.carepreventioncsv.model.domain.CarePreventionCsvColumn;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EndDate {

    private final String endDate;
    private static final int LENGTH = 6;

    public EndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getFormatErrorMessage() {        StringBuilder errorMessageBuilder = new StringBuilder();

        if (!validateCharcter()) {
            errorMessageBuilder.append("数字0~9以外が含まれています.");
        }

        if (!validateLength()) {
            errorMessageBuilder.append("6桁ではありません.");
        }

        return errorMessageBuilder.toString();
    }

    private boolean validateCharcter() {
        String regex = "[0-9]*";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(endDate);
        return m.matches();
    }

    private boolean validateLength() {
        return endDate.length() == LENGTH;
    }

}
