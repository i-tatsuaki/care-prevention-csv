package com.tatsuaki.carepreventioncsv.model.domain.CarePreventionCsvColumn;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class PaymentLimitDivision {

    private final String paymentLimitDivision;

    public enum Code {

        TARGET("3", "対象");

        private String code;
        private String japanese;

        Code(String code, String japanese) {
            this.code = code;
            this.japanese = japanese;
        }

        public String getCode() {
            return code;
        }

        public String getJapanese() {
            return japanese;
        }

        public static boolean contains(String targetCode) {
            List<PaymentLimitDivision.Code> targetCodes = Arrays.stream(values())
                    .filter( value -> value.getCode().equals(targetCode) )
                    .limit(1)
                    .collect(toList());

            return targetCodes.size() != 0;
        }

        public static String getJapanese(String targetCode) {
            List<PaymentLimitDivision.Code> targetCodes = Arrays.stream(values())
                    .filter( value -> value.getCode().equals(targetCode) )
                    .limit(1)
                    .collect(toList());

            if (targetCodes.size() == 0) {
                return "";
            }

            return targetCodes.get(0).getJapanese();
        }
    }


    public PaymentLimitDivision(String paymentLimitDivision) {
        this.paymentLimitDivision = paymentLimitDivision;
    }

    public String getPaymentLimitDivision() {
        return paymentLimitDivision + getJapanese(paymentLimitDivision);
    }
    private String getJapanese(String paymentLimitDivision) {
        return validateCodeFormat() && !paymentLimitDivision.isEmpty() ?
                "(" + PaymentLimitDivision.Code.getJapanese(paymentLimitDivision) + ")" : "";
    }

    public String getFormatErrorMessage() {
        StringBuilder errorMessageBuilder = new StringBuilder();

        if (!validateCodeFormat()) {
            errorMessageBuilder.append("存在しない支給限度額対象区分コードです.");
        }

        return errorMessageBuilder.toString();
    }

    private boolean validateCodeFormat() {
        return paymentLimitDivision.isEmpty() || PaymentLimitDivision.Code.contains(paymentLimitDivision);
    }
}
