package com.tatsuaki.carepreventioncsv.model.domain.CarePreventionCsvColumn

class ServiceName(val serviceName: String) {

    val formatErrorMessage: String
        get() {
            val errorMessageBuilder = StringBuilder()

            if (!validateLength()) {
                errorMessageBuilder.append("文字数が64を超えています.")
            }

            return errorMessageBuilder.toString()
        }

    private fun validateLength(): Boolean {
        return serviceName.length <= LENGTH
    }

    companion object {
        private val LENGTH = 64
    }
}
