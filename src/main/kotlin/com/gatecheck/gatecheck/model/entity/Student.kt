package com.gatecheck.gatecheck.model.entity

import org.springframework.data.annotation.Id
import java.util.*

data class Student(
        @Id override val id: UUID,
        override val name: String,
        override val username: String,
        override val email: String,
        override val password: String,
        override val profilePath: String,
        val instructors: Set<UUID>,
        val parents: Set<UUID>,
        val school: String
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