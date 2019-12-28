package com.tatsuaki.carepreventioncsv.model.domain.CarePreventionCsvColumn;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UnitNumber {

    private final String unitNumber;
    private static final int LENGTH = 5;

    public UnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public String getFormatErrorMessage() {
        StringBuilder errorMessageBuilder = new StringBuilder();

        if (!validateCharcter()) {
            errorMessageBuilder.append("単位数表現が誤っています.");
        }

        if (!validateLength()) {
            errorMessageBuilder.append("最大桁5桁を超えています.");
        }

        return errorMessageBuilder.toString();
    }

    private boolean validateCharcter() {
        String regex = "[0-9]*|-[0-9]*";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(unitNumber);
        return m.matches();
    }

    private boolean validateLength() {
        return unitNumber.length() <= LENGTH;
    }

}
