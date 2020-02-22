package com.tatsuaki.carepreventioncsv.model.domain

import com.tatsuaki.carepreventioncsv.model.domain.csvcolumn.*

import java.util.Arrays

class CarePreventionCsvItem private constructor(columns: Array<String>) {

    // 証記載保険者番号
    private val insureNumber: InsureNumber?

    // サービス種類コード
    private val serviceKindCode: ServiceKindCode?

    // サービス項目コード
    private val serviceCode: ServiceCode?

    // 適用開始年月
    private val startDate: StartDate?

    // 適用終了年月
    private val endDate: EndDate?

    // サービス名称
    private val serviceName: ServiceName?

    // 単位数
    private val unitNumber: UnitNumber?

    // 算定単位
    private val countUnit: CountUnit?

    // 制限日数・回数
    private val limitCount: LimitCount?

    // 算定回数制限期間
    private val termLimitCount: TermLimitCount?

    // 支給限度額対象区分
    private val paymentLimitDivision: PaymentLimitDivision?

    // 予備項目
    private val spareColumn: SpareColumn?

    // 給付率
    private val paymentRate: PaymentRate?

    // 利用者負担額
    private val insuredPayment: InsuredPayment?

    // 事業対象者実施区分
    private val implementationDivisionForTarget: ImplementationDivisionForTarget?

    // 要支援１受給者実施区分
    private val implementationDivisionForSupportNeed1: ImplementationDivisionForSupportNeed1?

    // 要支援２受給者実施区分
    private val implementationDivisionForSupportNeed2: ImplementationDivisionForSupportNeed2?

    // 国保連合会委託区分
    private val consignmentDivisionInsuranceAssociation: ConsignmentDivisionInsuranceAssociation?

    // 作成年月日
    private val createdDate: CreatedDate?

    // エラーフラグ
    val isError: Boolean

    // エラーメッセージ
    val errorMessage: String

    val serviceKindCodeFormatErrorMessage: String
        get() = serviceKindCode!!.formatErrorMessage

    val serviceCodeFormatErrorMessage: String
        get() = serviceCode!!.formatErrorMessage

    val startDateFormatErrorMessage: String
        get() = startDate!!.formatErrorMessage

    val endDateFormatErrorMessage: String
        get() = endDate!!.formatErrorMessage

    val serviceNameFormatErrorMessage: String
        get() = serviceName!!.formatErrorMessage

    val unitNumberFormatErrorMessage: String
        get() = unitNumber!!.formatErrorMessage

    val countUnitFormatErrorMessage: String
        get() = countUnit!!.formatErrorMessage

    val limitCountFormatErrorMessage: String
        get() = limitCount!!.formatErrorMessage

    val termLimitCountFormatErrorMessage: String
        get() = termLimitCount!!.formatErrorMessage

    val paymentLimitDivisionFormatErrorMessage: String
        get() = paymentLimitDivision!!.formatErrorMessage

    val spareColumnFormatErrorMessage: String
        get() = spareColumn!!.formatErrorMessage

    val paymentRateFormatErrorMessage: String
        get() = paymentRate!!.formatErrorMessage

    val insuredPaymentFormatErrorMessage: String
        get() = insuredPayment!!.formatErrorMessage

    val implementationDivisionForTargetFormatErrorMessage: String
        get() = implementationDivisionForTarget!!.formatErrorMessage

    val implementationDivisionForSupportNeed1FormatErrorMessage: String
        get() = implementationDivisionForSupportNeed1!!.formatErrorMessage

    val implementationDivisionForSupportNeed2FormatErrorMessage: String
        get() = implementationDivisionForSupportNeed2!!.formatErrorMessage

    val consignmentDivisionInsuranceAssociationFormatErrorMessage: String
        get() = consignmentDivisionInsuranceAssociation!!.formatErrorMessage

    val createdDateFormatErrorMessage: String
        get() = createdDate!!.formatErrorMessage

    init {
        // カラム数チェック
        if (columns.size != CSV_COLUMN_NUMBER) {
            isError = true
            errorMessage = "カラム数が誤っています:$columns"

            this.insureNumber = null
            this.serviceKindCode = null
            this.serviceCode = null
            this.startDate = null
            this.endDate = null
            this.serviceName = null
            this.unitNumber = null
            this.countUnit = null
            this.limitCount = null
            this.termLimitCount = null
            this.paymentLimitDivision = null
            this.spareColumn = null
            this.paymentRate = null
            this.insuredPayment = null
            this.implementationDivisionForTarget = null
            this.implementationDivisionForSupportNeed1 = null
            this.implementationDivisionForSupportNeed2 = null
            this.consignmentDivisionInsuranceAssociation = null
            this.createdDate = null

        } else {

            this.isError = false
            this.errorMessage = ""

            this.insureNumber = InsureNumber(columns[0])
            this.serviceKindCode = ServiceKindCode(columns[1])
            this.serviceCode = ServiceCode(columns[2])
            this.startDate = StartDate(columns[3])
            this.endDate = EndDate(columns[4])
            this.serviceName = ServiceName(columns[5])
            this.unitNumber = UnitNumber(columns[6])
            this.countUnit = CountUnit(columns[7])
            this.limitCount = LimitCount(columns[8])
            this.termLimitCount = TermLimitCount(columns[9])
            this.paymentLimitDivision = PaymentLimitDivision(columns[10])
            this.spareColumn = SpareColumn(columns[11])
            this.paymentRate = PaymentRate(columns[12])
            this.insuredPayment = InsuredPayment(columns[13])
            this.implementationDivisionForTarget = ImplementationDivisionForTarget(columns[14])
            this.implementationDivisionForSupportNeed1 = ImplementationDivisionForSupportNeed1(columns[15])
            this.implementationDivisionForSupportNeed2 = ImplementationDivisionForSupportNeed2(columns[16])
            this.consignmentDivisionInsuranceAssociation = ConsignmentDivisionInsuranceAssociation(columns[17])
            this.createdDate = CreatedDate(columns[18])
        }
    }

    fun getInsureNumber(): CsvColumn? {
        return insureNumber
    }

    fun getServiceKindCode(): String {
        return serviceKindCode!!.serviceKindCode
    }

    fun getServiceCode(): String {
        return serviceCode!!.serviceCode
    }

    fun getStartDate(): String {
        return startDate!!.startDate
    }

    fun getEndDate(): String {
        return endDate!!.endDate
    }

    fun getServiceName(): String {
        return serviceName!!.serviceName
    }

    fun getUnitNumber(): String {
        return unitNumber!!.unitNumber
    }

    fun getCountUnit(): String {
        return countUnit!!.getCountUnit()
    }

    fun getLimitCount(): String {
        return limitCount!!.limitCount
    }

    fun getTermLimitCount(): String {
        return termLimitCount!!.getTermLimitCount()
    }

    fun getPaymentLimitDivision(): String {
        return paymentLimitDivision!!.getPaymentLimitDivision()
    }

    fun getSpareColumn(): String {
        return spareColumn!!.spareColumn
    }

    fun getPaymentRate(): String {
        return paymentRate!!.paymentRate
    }

    fun getInsuredPayment(): String {
        return insuredPayment!!.insuredPayment
    }

    fun getImplementationDivisionForTarget(): String {
        return implementationDivisionForTarget!!.getImplementationDivisionForTarget()
    }

    fun getImplementationDivisionForSupportNeed1(): String {
        return implementationDivisionForSupportNeed1!!.getImplementationDivisionForSupportNeed1()
    }

    fun getImplementationDivisionForSupportNeed2(): String {
        return implementationDivisionForSupportNeed2!!.implementationDivisionForSupportNeed2
    }

    fun getConsignmentDivisionInsuranceAssociation(): String {
        return consignmentDivisionInsuranceAssociation!!.description
    }

    fun getCreatedDate(): String {
        return createdDate!!.createdDate
    }

    override fun equals(other: Any?): Boolean {

        // TODO 実装する
        if (other is CarePreventionCsvItem) {
            return this.insureNumber!!.equals(other.insureNumber)
        }

        return false
    }

    companion object {

        private val CSV_COLUMN_NUMBER = 19

        fun CreateFromCsvLine(columns: Array<String>): CarePreventionCsvItem {
            return CarePreventionCsvItem(columns)
        }
    }

}
