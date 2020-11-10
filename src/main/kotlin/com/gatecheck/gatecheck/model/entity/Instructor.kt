package com.gatecheck.gatecheck.model.entity

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*
import javax.validation.constraints.Email
import javax.validation.constraints.Size

@Document(collection = "users")
@TypeAlias("instructor")
class Instructor @JsonCreator constructor(
        @JsonProperty id: UUID,
        @JsonProperty name: String,
        @Size(min = 3, max = 16, message = "Name must be between 3 and 16 characters.") @JsonProperty username: String,
        @Email(message = "Must provide valid email.") @JsonProperty email: String,
        @Size(min = 8, max = 32, message = "Password must be between 8 and 32 characters.") @JsonProperty password: String,
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