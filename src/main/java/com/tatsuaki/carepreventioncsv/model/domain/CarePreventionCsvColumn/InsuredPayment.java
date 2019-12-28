package com.tatsuaki.carepreventioncsv.model.domain.CarePreventionCsvColumn;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InsuredPayment {

    private final String insuredPayment;
    private static final int LENGTH = 5;

    public InsuredPayment(String insuredPayment) {
        this.insuredPayment = insuredPayment;
    }

    public String getInsuredPayment() {
        return insuredPayment;
    }

    public String getFormatErrorMessage() {
        StringBuilder errorMessageBuilder = new StringBuilder();

        if (!validateCharcter()) {
            errorMessageBuilder.append("数字0~9以外が含まれています.");
        }

        if (!validateLength()) {
            errorMessageBuilder.append("1~5桁ではありません.");
        }

        return errorMessageBuilder.toString();
    }

    private boolean validateCharcter() {
        String regex = "[0-9]*";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(insuredPayment);
        return m.matches();
    }

    private boolean validateLength() {
        return insuredPayment.length() <= LENGTH;
    }

}
