package com.gatecheck.gatecheck.repository.user

import com.gatecheck.gatecheck.model.entity.User
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface GenericUserRepository<T : User> : MongoRepository<T, UUID> {
    fun findAllByName(name: String): Set<T>
    fun findByUsername(username: String): T?
    fun findByEmail(email: String): T?
}