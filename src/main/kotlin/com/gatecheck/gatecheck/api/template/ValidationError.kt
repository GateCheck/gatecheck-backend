package com.gatecheck.gatecheck.api.template

import com.fasterxml.jackson.annotation.JsonInclude


data class ValidationError(val error: String) {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private val success = "false"
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private val errors: MutableList<String> = mutableListOf()

    fun addValidationError(error: String) {
        errors.add(error)
    }

    companion object {
        fun build(vararg errors: String): ValidationError {
            val validationError = ValidationError("Validation failed. " + errors.size + " error(s)")
            for (error in errors) {
                validationError.addValidationError(error)
            }

            return validationError
        }
    }
}