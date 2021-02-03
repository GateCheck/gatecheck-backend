package com.gatecheck.gatecheck.api.template

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.http.HttpStatus

data class SuccessResponse @JsonCreator constructor(
        @JsonProperty val success: Boolean,
        @JsonProperty val status: Int,
        @JsonInclude(JsonInclude.Include.NON_EMPTY) @JsonProperty(required = false) val message: String = ""
) : Response(HttpStatus.valueOf(status))