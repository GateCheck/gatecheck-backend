package com.gatecheck.gatecheck.model.entity

import java.util.*


abstract class User(
        open val id: UUID,
        open val name: String,
        open val username: String,
        open val email: String,
        open val password: String,
        open val profilePath: String
) {
    constructor(id: UUID, user: User) : this(id, user.name, user.username, user.email, user.password, user.profilePath)
}