package com.gatecheck.gatecheck.api.template

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class SuccessResponse @JsonCreator constructor(
        @JsonProperty val success: Boolean,
        @JsonProperty val status: Int,
        @JsonProperty(required = false) val message: String? = null
) : Response