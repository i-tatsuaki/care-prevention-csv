package com.tatsuaki.carepreventioncsv.model.domain;

import com.tatsuaki.carepreventioncsv.model.domain.CarePreventionCsvColumn.*;

import java.util.Arrays;
import java.util.List;

public class CarePreventionCsvItem {

    // 証記載保険者番号
    final private InsureNumber insureNumber;

    // サービス種類コード
    final private ServiceKindCode serviceKindCode;

    // サービス項目コード
    final private ServiceCode serviceCode;

    // 適用開始年月
    final private StartDate startDate;

    // 適用終了年月
    final private EndDate endDate;

    // サービス名称
    final private ServiceName serviceName;

    // 単位数
    final private UnitNumber unitNumber;

    // 算定単位
    final private CountUnit countUnit;

    // 制限日数・回数
    final private LimitCount limitCount;

    // 算定回数制限期間
    final private TermLimitCount termLimitCount;

    // 支給限度額対象区分
    final private PaymentLimitDivision paymentLimitDivision;

    // 予備項目
    final private SpareColumn spareColumn;

    // 給付率
    final private PaymentRate paymentRate;

    // 利用者負担額
    final private InsuredPayment insuredPayment;

    // 事業対象者実施区分
    final private ImplementationDivisionForTarget implementationDivisionForTarget;

    // 要支援１受給者実施区分
    final private ImplementationDivisionForSupportNeed1 implementationDivisionForSupportNeed1;

    // 要支援２受給者実施区分
    final private ImplementationDivisionForSupportNeed2 implementationDivisionForSupportNeed2;

    // 国保連合会委託区分
    final private ConsignmentDivisionInsuranceAssociation consignmentDivisionInsuranceAssociation;

    // 作成年月日
    final private CreatedDate createdDate;

    // エラーフラグ
    final private boolean error;

    // エラーメッセージ
    final private String errorMessage;

    private static final int CSV_COLUMN_NUMBER = 19;

    private CarePreventionCsvItem(String csvLine) {

        String[] array = csvLine.split(",");
        List<String> columns = Arrays.asList(array);

        // カラム数チェック
        if (columns.size() != CSV_COLUMN_NUMBER) {
            error = true;
            errorMessage = "カラム数が誤っています:" + csvLine;

            this.insureNumber = null;
            this.serviceKindCode = null;
            this.serviceCode = null;
            this.startDate = null;
            this.endDate = null;
            this.serviceName = null;
            this.unitNumber = null;
            this.countUnit = null;
            this.limitCount = null;
            this.termLimitCount = null;
            this.paymentLimitDivision = null;
            this.spareColumn = null;
            this.paymentRate = null;
            this.insuredPayment = null;
            this.implementationDivisionForTarget = null;
            this.implementationDivisionForSupportNeed1 = null;
            this.implementationDivisionForSupportNeed2 = null;
            this.consignmentDivisionInsuranceAssociation = null;
            this.createdDate = null;

            return;
        }

        this.error = false;
        this.errorMessage = "";

        this.insureNumber = new InsureNumber(columns.get(0));
        this.serviceKindCode = new ServiceKindCode(columns.get(1));
        this.serviceCode = new ServiceCode(columns.get(2));
        this.startDate = new StartDate(columns.get(3));
        this.endDate = new EndDate(columns.get(4));
        this.serviceName = new ServiceName(columns.get(5));
        this.unitNumber = new UnitNumber(columns.get(6));
        this.countUnit = new CountUnit(columns.get(7));
        this.limitCount = new LimitCount(columns.get(8));
        this.termLimitCount = new TermLimitCount(columns.get(9));
        this.paymentLimitDivision = new PaymentLimitDivision(columns.get(10));
        this.spareColumn = new SpareColumn(columns.get(11));
        this.paymentRate = new PaymentRate(columns.get(12));
        this.insuredPayment = new InsuredPayment(columns.get(13));
        this.implementationDivisionForTarget = new ImplementationDivisionForTarget(columns.get(14));
        this.implementationDivisionForSupportNeed1 = new ImplementationDivisionForSupportNeed1(columns.get(15));
        this.implementationDivisionForSupportNeed2 = new ImplementationDivisionForSupportNeed2(columns.get(16));
        this.consignmentDivisionInsuranceAssociation = new ConsignmentDivisionInsuranceAssociation(columns.get(17));
        this.createdDate = new CreatedDate(columns.get(18));
    }

    public String getInsureNumber() {
        return insureNumber.getInsureNumber();
    }

    public String getInsureNumberFormatErrorMessage() {
        return insureNumber.getFormatErrorMessage();
    }

    public String getServiceKindCode() {
        return serviceKindCode.getServiceKindCode();
    }

    public String getServiceKindCodeFormatErrorMessage() {
        return serviceKindCode.getFormatErrorMessage();
    }

    public String getServiceCode() {
        return serviceCode.getServiceCode();
    }

    public String getServiceCodeFormatErrorMessage() {
        return serviceCode.getFormatErrorMessage();
    }

    public String getStartDate() {
        return startDate.getStartDate();
    }

    public String getStartDateFormatErrorMessage() {
        return startDate.getFormatErrorMessage();
    }

    public String getEndDate() {
        return endDate.getEndDate();
    }

    public String getEndDateFormatErrorMessage() {
        return endDate.getFormatErrorMessage();
    }

    public String getServiceName() {
        return serviceName.getServiceName();
    }

    public String getServiceNameFormatErrorMessage() {
        return serviceName.getFormatErrorMessage();
    }

    public String getUnitNumber() {
        return unitNumber.getUnitNumber();
    }

    public String getUnitNumberFormatErrorMessage() {
        return unitNumber.getFormatErrorMessage();
    }

    public String getCountUnit() {
        return countUnit.getCountUnit();
    }

    public String getCountUnitFormatErrorMessage() {
        return countUnit.getFormatErrorMessage();
    }

    public String getLimitCount() {
        return limitCount.getLimitCount();
    }

    public String getLimitCountFormatErrorMessage() {
        return limitCount.getFormatErrorMessage();
    }

    public String getTermLimitCount() {
        return termLimitCount.getTermLimitCount();
    }

    public String getTermLimitCountFormatErrorMessage() {
        return termLimitCount.getFormatErrorMessage();
    }

    public String getPaymentLimitDivision() {
        return paymentLimitDivision.getPaymentLimitDivision();
    }

    public String getPaymentLimitDivisionFormatErrorMessage() {
        return paymentLimitDivision.getFormatErrorMessage();
    }

    public String getSpareColumn() {
        return spareColumn.getSpareColumn();
    }

    public String getSpareColumnFormatErrorMessage() {
        return spareColumn.getFormatErrorMessage();
    }

    public String getPaymentRate() {
        return paymentRate.getPaymentRate();
    }

    public String getPaymentRateFormatErrorMessage() {
        return paymentRate.getFormatErrorMessage();
    }

    public String getInsuredPayment() {
        return insuredPayment.getInsuredPayment();
    }

    public String getInsuredPaymentFormatErrorMessage() {
        return insuredPayment.getFormatErrorMessage();
    }

    public String getImplementationDivisionForTarget() {
        return implementationDivisionForTarget.getImplementationDivisionForTarget();
    }

    public String getImplementationDivisionForTargetFormatErrorMessage() {
        return implementationDivisionForTarget.getFormatErrorMessage();
    }

    public String getImplementationDivisionForSupportNeed1() {
        return implementationDivisionForSupportNeed1.getImplementationDivisionForSupportNeed1();
    }

    public String getImplementationDivisionForSupportNeed1FormatErrorMessage() {
        return implementationDivisionForSupportNeed1.getFormatErrorMessage();
    }
    public String getImplementationDivisionForSupportNeed2() {
        return implementationDivisionForSupportNeed2.getImplementationDivisionForSupportNeed2();
    }

    public String getImplementationDivisionForSupportNeed2FormatErrorMessage() {
        return implementationDivisionForSupportNeed2.getFormatErrorMessage();
    }

    public String getConsignmentDivisionInsuranceAssociation() {
        return consignmentDivisionInsuranceAssociation.getConsignmentDivisionInsuranceAssociation();
    }

    public String getConsignmentDivisionInsuranceAssociationFormatErrorMessage() {
        return consignmentDivisionInsuranceAssociation.getFormatErrorMessage();
    }

    public String getCreatedDate() {
        return createdDate.getCreatedDate();
    }

    public String getCreatedDateFormatErrorMessage() {
        return createdDate.getFormatErrorMessage();
    }

    public boolean isError() {
        return error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    static public CarePreventionCsvItem CreateFromCsvLine(String csvLine) {
        return new CarePreventionCsvItem(csvLine);
    }

}
