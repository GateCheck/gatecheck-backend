package com.gatecheck.gatecheck.api.template

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.gatecheck.gatecheck.model.Request

@JsonInclude(JsonInclude.Include.NON_NULL)
data class DefaultRequestResponse @JsonCreator constructor(
        @JsonProperty val success: Boolean,
        @JsonProperty val request: Request? = null,
        @JsonProperty val requests: List<Request>? = null
)