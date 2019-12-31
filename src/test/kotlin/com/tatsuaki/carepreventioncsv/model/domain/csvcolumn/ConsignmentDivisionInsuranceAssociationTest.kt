package com.tatsuaki.carepreventioncsv.model.domain.csvcolumn

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class ConsignmentDivisionInsuranceAssociationCodeTest {

    companion object {
        @JvmStatic
        fun parameters_contains() = listOf(
                arguments("1", true),
                arguments("0", false)
        )

        @JvmStatic
        fun parameters_getJapanese() = listOf(
                arguments("1", "委託する"),
                arguments("2", "委託しない")
        )

        @JvmStatic
        fun parameters_get() = listOf(
                arguments("1", "1(委託する)", ""),
                arguments("2", "2(委託しない)", ""),
                arguments("0", "0", "存在しない事業対象者実施区分コードです.")
        )
    }

    @ParameterizedTest
    @MethodSource("parameters_contains")
    fun contains (

            code: String,
            isExistsCode: Boolean
    ) {
        // when
        val actual = ConsignmentDivisionInsuranceAssociation.Code.contains(code)

        // then
        assertThat(actual).isEqualTo(isExistsCode)
    }

    @ParameterizedTest
    @MethodSource("parameters_getJapanese")
    fun getJapanese (
            code: String,
            expected: String
    )  {
        // when
        val actual = ConsignmentDivisionInsuranceAssociation.Code.getJapanese(code)

        // then
        assertThat(actual).isEqualTo(expected)
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

        // when
        val actualConsignmentDivisionInsuranceAssociation =

        // then
        assertThat(target.getConsignmentDivisionInsuranceAssociation()).isEqualTo(expectedConsignmentDivisionInsuranceAssociation)
        assertThat(target.formatErrorMessage).isEqualTo(expectedFormatErrorMessage)
    }
}
