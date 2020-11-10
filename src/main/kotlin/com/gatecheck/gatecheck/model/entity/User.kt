package com.gatecheck.gatecheck.model.entity

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*
import javax.validation.constraints.Email
import javax.validation.constraints.Size

@Document(collection = "users")
// @TypeAlias(className) on inheriting classes to support inheritance for MongoRepository
abstract class User(
        @Id open val id: UUID,
        @JsonProperty open val name: String,
        @Indexed(unique = true) @Size(min = 3, max = 16, message = "Name must be between 3 and 16 characters.") @JsonProperty open val username: String,
        @Indexed(unique = true) @Email(message = "Must provide valid email.") @JsonProperty open val email: String,
        @Size(min = 8, max = 32, message = "Password must be between 8 and 32 characters.") @JsonProperty open var password: String,
        @Indexed(unique = true) @JsonProperty open var profilePath: String?
) {
    constructor(id: UUID, user: User) : this(id, user.name, user.username, user.email, user.password, user.profilePath)

    override fun toString(): String {
        return "${this::class.simpleName}(id=$id, name='$name', username='$username', email='$email', password='$password', profilePath=$profilePath)"
    }

}