package com.tatsuaki.carepreventioncsv.model.domain.CarePreventionCsvColumn;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class CountUnit {

    private final String countUnit;

    public enum Code {

        TIMES("01", "回数単位"),
        DAILY("02", "日単位"),
        MONTHLY("03", "月単位"),
        WEEKLY("05", "週単位");

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
            List<Code> targetCodes = Arrays.stream(values())
                    .filter( value -> value.getCode().equals(targetCode) )
                    .limit(1)
                    .collect(toList());

            return targetCodes.size() != 0;
        }

        public static String getJapanese(String targetCode) {
            List<Code> targetCodes = Arrays.stream(values())
                    .filter( value -> value.getCode().equals(targetCode) )
                    .limit(1)
                    .collect(toList());

            if (targetCodes.size() == 0) {
                return "";
            }

            return targetCodes.get(0).getJapanese();
        }
    }

    public CountUnit(String countUnit) {
        this.countUnit = countUnit;
    }

    public String getCountUnit() {
        return countUnit + getJapanese(countUnit);
    }

    private String getJapanese(String countUnit) {
        return validateCodeFormat() ?
                "(" + Code.getJapanese(countUnit) + ")" : "";
    }

    public String getFormatErrorMessage() {
        StringBuilder errorMessageBuilder = new StringBuilder();

        if (!validateCodeFormat()) {
            errorMessageBuilder.append("存在しない算定単位コードです.");
        }

        return errorMessageBuilder.toString();
    }

    private boolean validateCodeFormat() {
        return Code.contains(countUnit);
    }
}
