package com.tatsuaki.carepreventioncsv.model.domain.CarePreventionCsvColumn;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ImplementationDivisionForTarget {

    private final String implementationDivisionForTarget;

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
            List<ImplementationDivisionForTarget.Code> targetCodes = Arrays.stream(values())
                    .filter( value -> value.getCode().equals(targetCode) )
                    .limit(1)
                    .collect(toList());

            return targetCodes.size() != 0;
        }

        public static String getJapanese(String targetCode) {
            List<ImplementationDivisionForTarget.Code> targetCodes = Arrays.stream(values())
                    .filter( value -> value.getCode().equals(targetCode) )
                    .limit(1)
                    .collect(toList());

            if (targetCodes.size() == 0) {
                return "";
            }

            return targetCodes.get(0).getJapanese();
        }
    }

    public ImplementationDivisionForTarget(String implementationDivisionForTarget) {
        this.implementationDivisionForTarget = implementationDivisionForTarget;
    }

    public String getImplementationDivisionForTarget() {
        return implementationDivisionForTarget + getJapanese(implementationDivisionForTarget);
    }

    private String getJapanese(String implementationDivisionForTarget) {
        return validateCodeFormat() ?
                "(" + ImplementationDivisionForTarget.Code.getJapanese(implementationDivisionForTarget) + ")" : "";
    }

    public String getFormatErrorMessage() {
        StringBuilder errorMessageBuilder = new StringBuilder();

        if (!validateCodeFormat()) {
            errorMessageBuilder.append("存在しない事業対象者実施区分コードです.");
        }

        return errorMessageBuilder.toString();
    }

    private boolean validateCodeFormat() {
        return ImplementationDivisionForTarget.Code.contains(implementationDivisionForTarget);
    }
}
