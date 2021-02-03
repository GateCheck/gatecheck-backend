package com.gatecheck.gatecheck.api.template

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.http.HttpStatus

data class AuthRequest @JsonCreator constructor(
        @JsonProperty("username") val username: String,
        @JsonProperty("password") val password: String,
        @JsonProperty("stay_logged_in") val stayLoggedIn: Boolean?
) : Response(HttpStatus.OK)