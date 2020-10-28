package com.gatecheck.gatecheck.model.entity

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "users")
@TypeAlias("instructor")
class Instructor @JsonCreator constructor(
        @JsonProperty id: UUID,
        @JsonProperty name: String,
        @JsonProperty username: String,
        @JsonProperty email: String,
        @JsonProperty password: String,
        @JsonProperty profilePath: String?,
        @JsonProperty val students: Set<UUID>? = setOf(),
        @JsonProperty val schools: Set<String>? = setOf()
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
            students: Set<UUID> = setOf(),
            schools: Set<String> = setOf()
    ) : this(
            id,
            user.name,
            user.username,
            user.email,
            user.password,
            user.profilePath,
            students,
            schools
    )
}