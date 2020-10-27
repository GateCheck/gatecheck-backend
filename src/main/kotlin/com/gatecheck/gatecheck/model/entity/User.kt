package com.gatecheck.gatecheck.model.entity

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import java.util.*


abstract class User(
        @Id open val id: UUID,
        @JsonProperty open val name: String,
        @JsonProperty open val username: String,
        @JsonProperty open val email: String,
        @JsonProperty open val password: String,
        @JsonProperty open val profilePath: String?
) {
    constructor(id: UUID, user: User) : this(id, user.name, user.username, user.email, user.password, user.profilePath)
}