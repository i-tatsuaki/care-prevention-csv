package com.tatsuaki.carepreventioncsv.model.domain.CarePreventionCsvColumn;

public class ServiceName {

    private final String serviceName;
    private static final int LENGTH = 64;

    public ServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getFormatErrorMessage() {
        StringBuilder errorMessageBuilder = new StringBuilder();

        if (!validateLength()) {
            errorMessageBuilder.append("文字数が64を超えています.");
        }

        return errorMessageBuilder.toString();
    }

    private boolean validateLength() {
        return serviceName.length() <= LENGTH;
    }
}
