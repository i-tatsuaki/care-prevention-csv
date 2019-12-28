package com.tatsuaki.carepreventioncsv.model.domain.CarePreventionCsvColumn;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toList;

public class LimitCount {

    private final String limitCount;
    private static final int LENGTH = 2;

    public LimitCount(String limitCount) {
        this.limitCount = limitCount;
    }

    public String getLimitCount() {
        return limitCount;
    }

    public String getFormatErrorMessage() {
        StringBuilder errorMessageBuilder = new StringBuilder();

        if (!validateCharcter()) {
            errorMessageBuilder.append("数字0~9以外が含まれています.");
        }

        if (!validateLength()) {
            errorMessageBuilder.append("1~2桁ではありません.");
        }

        return errorMessageBuilder.toString();
    }

    private boolean validateCharcter() {
        String regex = "[0-9]*";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(limitCount);
        return m.matches();
    }

    private boolean validateLength() {
        return limitCount.isEmpty() || limitCount.length() == LENGTH;
    }

}
