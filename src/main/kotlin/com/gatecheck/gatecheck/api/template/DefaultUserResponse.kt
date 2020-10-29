package com.gatecheck.gatecheck.api.template

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.gatecheck.gatecheck.model.entity.User

data class DefaultUserResponse @JsonCreator constructor(
        @JsonProperty val success: Boolean,
        @JsonProperty val user: User? = null,
        @JsonProperty val users: Set<User>? = null
)