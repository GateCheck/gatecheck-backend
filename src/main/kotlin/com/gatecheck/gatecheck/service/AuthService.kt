package com.gatecheck.gatecheck.service

import com.gatecheck.gatecheck.api.template.AuthRequest
import com.gatecheck.gatecheck.dao.auth.AuthDao
import com.gatecheck.gatecheck.model.entity.User
import com.gatecheck.gatecheck.security.CurrentUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class AuthService @Autowired constructor(@Qualifier("authDao") private val authDao: AuthDao) {
    @Throws(ResponseStatusException::class)
    fun login(authRequest: AuthRequest): String {
        if (CurrentUser.isLoggedIn) {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "You must be logged out in order to login!")
        }

        return authDao.login(authRequest)
    }

    @Throws(ResponseStatusException::class)
    fun <T : User> register(user: T): String {
        if (CurrentUser.isLoggedIn) {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "You must be logged out in order to login!")
        }

        return authDao.register(user)
    }
}