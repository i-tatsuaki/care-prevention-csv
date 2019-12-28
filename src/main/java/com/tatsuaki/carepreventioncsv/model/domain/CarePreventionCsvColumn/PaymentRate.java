package com.tatsuaki.carepreventioncsv.model.domain.CarePreventionCsvColumn;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PaymentRate {

    private final String paymentRate;
    private static final int LENGTH = 3;

    public PaymentRate(String paymentRate) {
        this.paymentRate = paymentRate;
    }

    public String getPaymentRate() {
        return paymentRate;
    }

    public String getFormatErrorMessage() {
        StringBuilder errorMessageBuilder = new StringBuilder();

        if (!validateCharcter()) {
            errorMessageBuilder.append("数字0~9以外が含まれています.");
        }

        if (!validateLength()) {
            errorMessageBuilder.append("1~3桁ではありません.");
        }

        return errorMessageBuilder.toString();
    }

    private boolean validateCharcter() {
        String regex = "[0-9]*";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(paymentRate);
        return m.matches();
    }

    private boolean validateLength() {
        return paymentRate.length() <= LENGTH;
    }
}
