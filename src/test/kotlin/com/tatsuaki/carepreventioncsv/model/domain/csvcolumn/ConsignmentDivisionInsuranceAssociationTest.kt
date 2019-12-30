package com.tatsuaki.carepreventioncsv.model.domain.csvcolumn

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class ConsignmentDivisionInsuranceAssociationCodeTest(
        val code: String,
        val isExistsCode: Boolean
) {

    companion object {
        @Parameterized.Parameters
        @JvmStatic
        fun data() = listOf(
                arrayOf("1", true),
                arrayOf("0", false)
        )
    }

    @Test
    fun `Code_contains`() {

        // when
        val actual = ConsignmentDivisionInsuranceAssociation.Code.contains(code)

        // then
        assertThat(actual).isEqualTo(isExistsCode)
    }
}