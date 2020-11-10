package com.gatecheck.gatecheck.api.template

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class AuthResponse @JsonCreator constructor(
        @JsonProperty("token") val token: String?,
        @JsonProperty("success") val success: Boolean
) : Response