package com.gatecheck.gatecheck.model.entity

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import java.util.*

data class Student(
        @Id override val id: UUID,
        @JsonProperty override val name: String,
        @JsonProperty override val username: String,
        @JsonProperty override val email: String,
        @JsonProperty override val password: String,
        @JsonProperty override val profilePath: String?,
        @JsonProperty val instructors: Set<UUID>? = setOf(),
        @JsonProperty val parents: Set<UUID>? = setOf(),
        @JsonProperty val school: String
) : User(
        id,
        name,
        username,
        email,
        password,
        profilePath
) {
    constructor(
            id: UUID,
            user: User,
            instructors: Set<UUID> = setOf(),
            parents: Set<UUID> = setOf(),
            school: String = ""
    ) : this(
            id,
            user.name,
            user.username,
            user.email,
            user.password,
            user.profilePath,
            instructors,
            parents,
            school
    )
}