package com.tatsuaki.carepreventioncsv.model.domain.CarePreventionCsvColumn;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class TermLimitCount {

    private final String termLimitCount;

    public enum Code {

        MONTHLY("01", "月単位"),
        DAILY("08", "日単位"),
        WEEKLY("16", "週単位");

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

    public TermLimitCount(String termLimitCount) {
        this.termLimitCount = termLimitCount;
    }

    public String getTermLimitCount() {
        return termLimitCount  + getJapanese(termLimitCount);
    }

    private String getJapanese(String termLimitCount) {
        return validateCodeFormat() && !termLimitCount.isEmpty() ?
                "(" + TermLimitCount.Code.getJapanese(termLimitCount) + ")" : "";
    }

    public String getFormatErrorMessage() {
        StringBuilder errorMessageBuilder = new StringBuilder();

        if (!validateCodeFormat()) {
            errorMessageBuilder.append("存在しない算定回数制限期間コードです.");
        }

        return errorMessageBuilder.toString();
    }

    private boolean validateCodeFormat() {
        return termLimitCount.isEmpty() || TermLimitCount.Code.contains(termLimitCount);
    }
}
