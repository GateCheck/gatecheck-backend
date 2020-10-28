package com.gatecheck.gatecheck.model.entity

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "users")
@TypeAlias("parent")
class Parent @JsonCreator constructor(
        @JsonProperty id: UUID,
        @JsonProperty name: String,
        @JsonProperty username: String,
        @JsonProperty email: String,
        @JsonProperty password: String,
        @JsonProperty profilePath: String?,
        @JsonProperty val children: Set<UUID>? = setOf()
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
            children: Set<UUID> = setOf()
    ) : this(
            id,
            user.name,
            user.username,
            user.email,
            user.password,
            user.profilePath,
            children
    )
}