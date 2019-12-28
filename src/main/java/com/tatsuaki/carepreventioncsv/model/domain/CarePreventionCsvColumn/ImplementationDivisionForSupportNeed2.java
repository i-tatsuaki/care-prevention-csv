package com.tatsuaki.carepreventioncsv.model.domain.CarePreventionCsvColumn;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ImplementationDivisionForSupportNeed2 {

    private final String implementationDivisionForSupportNeed2;


    public enum Code {

        TARGET("1", "実施不可"),
        NON_TARGET("2", "実施可");

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
            List<ImplementationDivisionForSupportNeed2.Code> targetCodes = Arrays.stream(values())
                    .filter( value -> value.getCode().equals(targetCode) )
                    .limit(1)
                    .collect(toList());

            return targetCodes.size() != 0;
        }

        public static String getJapanese(String targetCode) {
            List<ImplementationDivisionForSupportNeed2.Code> targetCodes = Arrays.stream(values())
                    .filter( value -> value.getCode().equals(targetCode) )
                    .limit(1)
                    .collect(toList());

            if (targetCodes.size() == 0) {
                return "";
            }

            return targetCodes.get(0).getJapanese();
        }
    }

    public ImplementationDivisionForSupportNeed2(String implementationDivisionForSupportNeed2) {
        this.implementationDivisionForSupportNeed2 = implementationDivisionForSupportNeed2;
    }

    public String getImplementationDivisionForSupportNeed2() {
        return implementationDivisionForSupportNeed2;
    }

    private String getJapanese(String implementationDivisionForSupportNeed2) {
        return validateCodeFormat() ?
                "(" + ImplementationDivisionForSupportNeed2.Code.getJapanese(implementationDivisionForSupportNeed2) + ")" : "";
    }

    public String getFormatErrorMessage() {
        StringBuilder errorMessageBuilder = new StringBuilder();

        if (!validateCodeFormat()) {
            errorMessageBuilder.append("存在しない要支援２受給者実施区分コードです.");
        }

        return errorMessageBuilder.toString();
    }

    private boolean validateCodeFormat() {
        return ImplementationDivisionForSupportNeed2.Code.contains(implementationDivisionForSupportNeed2);
    }
}
