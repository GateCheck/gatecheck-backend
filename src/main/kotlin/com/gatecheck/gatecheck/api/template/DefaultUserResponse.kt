package com.gatecheck.gatecheck.api.template

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.gatecheck.gatecheck.model.entity.User
import org.springframework.http.HttpStatus

@JsonInclude(JsonInclude.Include.NON_NULL)
data class DefaultUserResponse @JsonCreator constructor(
        @JsonProperty val success: Boolean,
        @JsonProperty val user: User? = null,
        @JsonProperty val users: Set<User>? = null
) : Response(if (success) HttpStatus.OK else HttpStatus.UNAUTHORIZED)