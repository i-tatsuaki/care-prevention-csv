package com.tatsuaki.carepreventioncsv.model.domain

import com.tatsuaki.carepreventioncsv.model.domain.csvcolumn.*

import java.util.Arrays

class CarePreventionCsvItem private constructor(columns: Array<String>) {

    // 証記載保険者番号
    val insureNumber: InsureNumber?

    // サービス種類コード
    val serviceKindCode: ServiceKindCode?

    // サービス項目コード
    val serviceCode: ServiceCode?

    // 適用開始年月
    val startDate: StartDate?

    // 適用終了年月
    val endDate: EndDate?

    // サービス名称
    val serviceName: ServiceName?

    // 単位数
    val unitNumber: UnitNumber?

    // 算定単位
    val countUnit: CountUnit?

    // 制限日数・回数
    val limitCount: LimitCount?

    // 算定回数制限期間
    val termLimitCount: TermLimitCount?

    // 支給限度額対象区分
    val paymentLimitDivision: PaymentLimitDivision?

    // 予備項目
    val spareColumn: SpareColumn?

    // 給付率
    val paymentRate: PaymentRate?

    // 利用者負担額
    val insuredPayment: InsuredPayment?

    // 事業対象者実施区分
    val implementationDivisionForTarget: ImplementationDivisionForTarget?

    // 要支援１受給者実施区分
    val implementationDivisionForSupportNeed1: ImplementationDivisionForSupportNeed1?

    // 要支援２受給者実施区分
    val implementationDivisionForSupportNeed2: ImplementationDivisionForSupportNeed2?

    // 国保連合会委託区分
    val consignmentDivisionInsuranceAssociation: ConsignmentDivisionInsuranceAssociation?

    // 作成年月日
    val createdDate: CreatedDate?

    // エラーフラグ
    val isError: Boolean

    // エラーメッセージ
    val errorMessage: String

    init {
        // カラム数チェック
        if (columns.size != CSV_COLUMN_NUMBER) {
            isError = true

            errorMessage = "カラム数が誤っています"

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

    fun getColumns(): List<CsvColumn?> {
        return mutableListOf(
                insureNumber,
                serviceKindCode,
                serviceCode,
                startDate,
                endDate,
                serviceName,
                unitNumber,
                countUnit,
                limitCount,
                termLimitCount,
                paymentLimitDivision,
                spareColumn,
                paymentRate,
                insuredPayment,
                implementationDivisionForTarget,
                implementationDivisionForSupportNeed1,
                implementationDivisionForSupportNeed2,
                consignmentDivisionInsuranceAssociation,
                createdDate
        )
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
