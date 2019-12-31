package com.tatsuaki.carepreventioncsv.model.domain.csvcolumn

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class ConsignmentDivisionInsuranceAssociationCodeTest {

    companion object {
        @JvmStatic
        fun parameters_get() = listOf(
                arguments("1", "1(委託する)", ""),
                arguments("2", "2(委託しない)", ""),
                arguments("0", "0", "存在しない事業対象者実施区分コードです.")
        )
    }

    @ParameterizedTest
    @MethodSource("parameters_get")
    fun get(
            code: String,
            expectedConsignmentDivisionInsuranceAssociation: String,
            expectedFormatErrorMessage: String
    ) {
        // given
        val target = ConsignmentDivisionInsuranceAssociation(code)

        // then
        assertThat(target.description).isEqualTo(expectedConsignmentDivisionInsuranceAssociation)
        assertThat(target.formatErrorMessage).isEqualTo(expectedFormatErrorMessage)
    }
}
