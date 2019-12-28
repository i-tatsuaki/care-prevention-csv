package com.tatsuaki.carepreventioncsv.model.domain.CarePreventionCsvColumn;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServiceCode {

    private final String serviceCode;
    private static final int LENGTH = 4;

    public ServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public String getFormatErrorMessage() {
        StringBuilder errorMessageBuilder = new StringBuilder();

        if (!validateCharcter()) {
            errorMessageBuilder.append("英数以外が含まれています.");
        }

        if (!validateLength()) {
            errorMessageBuilder.append("4桁ではありません.");
        }

        return errorMessageBuilder.toString();
    }

    private boolean validateCharcter() {
        String regex = "[0-9a-zA-Z]*";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(serviceCode);
        return m.matches();
    }

    private boolean validateLength() {
        return serviceCode.length() == LENGTH;
    }
}
