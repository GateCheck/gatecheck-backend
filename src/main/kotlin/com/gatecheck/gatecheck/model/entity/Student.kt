package com.gatecheck.gatecheck.model.entity

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Language
import java.util.*
import javax.validation.constraints.Email
import javax.validation.constraints.Size

@Document(collection = "users")
@TypeAlias("student")
class Student @JsonCreator constructor(
        @JsonProperty id: UUID = UUID.randomUUID(),
        @JsonProperty name: String,
        @Size(min = 3, max = 16, message = "Name must be between 3 and 16 characters.") @JsonProperty username: String,
        @Email(message = "Must provide valid email.") @JsonProperty email: String,
        @Size(min = 8, max = 32, message = "Password must be between 8 and 32 characters.") @JsonProperty password: String,
        @JsonProperty profilePath: String?,
        @JsonProperty(required = false, defaultValue = "he") language: String?,
        @JsonProperty val instructors: Set<UUID>? = setOf(),
        @JsonProperty val parents: Set<UUID>? = setOf(),
        @JsonProperty val school: String
) : User(
        id,
        name,
        username,
        email,
        password,
        profilePath,
        language
) {
    constructor(
            id: UUID,
            user: User,
            instructors: Set<UUID> = setOf(),
            parents: Set<UUID> = setOf(),
            school: String = "",
            language: String = ""
    ) : this(
            id,
            user.name,
            user.username,
            user.email,
            user.password,
            user.profilePath,
            school,
            instructors,
            parents,
            language
    )
}