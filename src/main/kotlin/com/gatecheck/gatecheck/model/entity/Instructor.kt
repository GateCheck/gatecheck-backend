package com.gatecheck.gatecheck.model.entity

import org.springframework.data.annotation.Id
import java.util.*

data class Instructor(
        @Id override val id: UUID,
        override val name: String,
        override val username: String,
        override val email: String,
        override val password: String,
        override val profilePath: String,
        val students: Set<UUID>,
        val schools: Set<String>
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