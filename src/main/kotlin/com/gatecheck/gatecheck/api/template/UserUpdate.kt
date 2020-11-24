package com.gatecheck.gatecheck.api.template

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class UserUpdate @JsonCreator constructor(
        @JsonProperty val name: String?,
        @JsonProperty val username: String?,
        @JsonProperty val email: String?,
        @JsonProperty val password: String?,
        @JsonProperty val profilePath: String?,
        @JsonProperty val school: Set<String>?,
        @JsonProperty val instructors: Set<UUID>?,
        @JsonProperty val parents: Set<UUID>?,
        @JsonProperty val students: Set<UUID>?,
        @JsonProperty val language: String?
)