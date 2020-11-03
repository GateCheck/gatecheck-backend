package com.gatecheck.gatecheck.dao.auth

import com.gatecheck.gatecheck.api.template.AuthRequest
import com.gatecheck.gatecheck.model.entity.User

interface AuthDao {
    fun login(authRequest: AuthRequest): String
    fun <T : User> register(user: T): String
    fun generateJwtToken(authRequest: AuthRequest): String
}