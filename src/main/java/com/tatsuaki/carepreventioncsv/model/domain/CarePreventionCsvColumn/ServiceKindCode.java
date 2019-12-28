package com.tatsuaki.carepreventioncsv.model.domain.CarePreventionCsvColumn;

import java.util.List;

public class ServiceKindCode {

    private final String serviceKindCode;

    public enum Code {
        A2,
        A3,
        A4,
        A6,
        A7,
        A8,
        A9,
        AA,
        AB,
        AC,
        AD,
        AE,
        AF
    }

    public ServiceKindCode(String serviceKindCode) {
        this.serviceKindCode = serviceKindCode;
    }

    public String getServiceKindCode() {
        return serviceKindCode;
    }

    public String getFormatErrorMessage() {
        StringBuilder errorMessageBuilder = new StringBuilder();

        if (!validateCodeFormat()) {
            errorMessageBuilder.append("存在しない総合事業サービス種類コードです.");
        }

        return errorMessageBuilder.toString();
    }

    private boolean validateCodeFormat() {
        try {
            Code.valueOf(serviceKindCode);
            return true;
        } catch(IllegalArgumentException e) {
            return false;
        }
    }
}
