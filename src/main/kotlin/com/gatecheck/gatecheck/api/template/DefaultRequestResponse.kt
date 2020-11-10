package com.gatecheck.gatecheck.api.template

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.gatecheck.gatecheck.model.Request

data class DefaultRequestResponse @JsonCreator constructor(
        @JsonProperty val success: Boolean,
        @JsonProperty val request: Request? = null,
        @JsonProperty val requests: Set<Request>? = null
)