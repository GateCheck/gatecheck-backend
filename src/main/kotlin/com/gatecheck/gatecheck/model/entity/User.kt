package com.gatecheck.gatecheck.model.entity

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "users")
// @TypeAlias(className) on inheriting classes to support inheritance for MongoRepository
abstract class User(
        @Id open val id: UUID,
        @JsonProperty open val name: String,
        @Indexed(unique = true) @JsonProperty open val username: String,
        @Indexed(unique = true) @JsonProperty open val email: String,
        @JsonProperty open val password: String,
        @Indexed(unique = true) @JsonProperty open val profilePath: String?
) {
    constructor(id: UUID, user: User) : this(id, user.name, user.username, user.email, user.password, user.profilePath)

    override fun toString(): String {
        return "${this::class.simpleName}(id=$id, name='$name', username='$username', email='$email', password='$password', profilePath=$profilePath)"
    }

}