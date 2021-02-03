package com.gatecheck.gatecheck.api.template

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.http.HttpStatus

data class AuthResponse @JsonCreator constructor(
        @JsonProperty("token") val token: String?,
        @JsonProperty("success") val success: Boolean
) : Response(if (success) HttpStatus.OK else HttpStatus.UNAUTHORIZED)