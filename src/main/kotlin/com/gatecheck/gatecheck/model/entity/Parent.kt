package com.gatecheck.gatecheck.model.entity

import org.springframework.data.annotation.Id
import java.util.*

data class Parent(
        @Id override val id: UUID,
        override val name: String,
        override val username: String,
        override val email: String,
        override val password: String,
        override val profilePath: String?,
        val children: Set<UUID>? = setOf()
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