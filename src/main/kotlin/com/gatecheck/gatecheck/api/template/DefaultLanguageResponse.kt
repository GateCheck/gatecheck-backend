package com.gatecheck.gatecheck.api.template

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class DefaultLanguageResponse(
        val success: Boolean,
        val language: Map<String, String>? = null,
        val languageCodes: Set<String>? = null
)