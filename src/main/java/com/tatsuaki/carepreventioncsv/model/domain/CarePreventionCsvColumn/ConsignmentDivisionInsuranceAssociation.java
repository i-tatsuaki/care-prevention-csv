package com.tatsuaki.carepreventioncsv.model.domain.CarePreventionCsvColumn;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ConsignmentDivisionInsuranceAssociation {

    private final String consignmentDivisionInsuranceAssociation;

    public enum Code {

        TARGET("1", "委託する"),
        NON_TARGET("2", "委託しない");

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
            List<ConsignmentDivisionInsuranceAssociation.Code> targetCodes = Arrays.stream(values())
                    .filter( value -> value.getCode().equals(targetCode) )
                    .limit(1)
                    .collect(toList());

            return targetCodes.size() != 0;
        }

        public static String getJapanese(String targetCode) {
            List<ConsignmentDivisionInsuranceAssociation.Code> targetCodes = Arrays.stream(values())
                    .filter( value -> value.getCode().equals(targetCode) )
                    .limit(1)
                    .collect(toList());

            if (targetCodes.size() == 0) {
                return "";
            }

            return targetCodes.get(0).getJapanese();
        }
    }

    public ConsignmentDivisionInsuranceAssociation(String consignmentDivisionInsuranceAssociation) {
        this.consignmentDivisionInsuranceAssociation = consignmentDivisionInsuranceAssociation;
    }

    public String getConsignmentDivisionInsuranceAssociation() {
        return consignmentDivisionInsuranceAssociation + getJapanese(consignmentDivisionInsuranceAssociation);
    }

    private String getJapanese(String consignmentDivisionInsuranceAssociation) {
        return validateCodeFormat() ?
                "(" + ConsignmentDivisionInsuranceAssociation.Code.getJapanese(consignmentDivisionInsuranceAssociation) + ")" : "";
    }

    public String getFormatErrorMessage() {
        StringBuilder errorMessageBuilder = new StringBuilder();

        if (!validateCodeFormat()) {
            errorMessageBuilder.append("存在しない事業対象者実施区分コードです.");
        }

        return errorMessageBuilder.toString();
    }

    private boolean validateCodeFormat() {
        return ConsignmentDivisionInsuranceAssociation.Code.contains(consignmentDivisionInsuranceAssociation);
    }
}
