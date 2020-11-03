package com.gatecheck.gatecheck.service

import com.gatecheck.gatecheck.api.template.AuthRequest
import com.gatecheck.gatecheck.dao.auth.AuthDao
import com.gatecheck.gatecheck.model.entity.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class AuthService @Autowired constructor(@Qualifier("authDao") private val authDao: AuthDao) {
    fun login(authRequest: AuthRequest): String = authDao.login(authRequest)

    fun <T : User> register(user: T): String = authDao.register(user)

    fun generateJwtToken(authRequest: AuthRequest): String = authDao.generateJwtToken(authRequest)
}