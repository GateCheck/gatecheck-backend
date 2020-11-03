package com.gatecheck.gatecheck.dao.auth

import com.gatecheck.gatecheck.api.template.AuthRequest
import com.gatecheck.gatecheck.model.entity.User
import com.gatecheck.gatecheck.repository.user.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository("authDao")
class AuthDataAccessService @Autowired constructor(
        private val userRepository: UserRepository
) : AuthDao {
    override fun login(authRequest: AuthRequest): String {
        TODO("Not yet implemented")
    }

    override fun <T : User> register(user: T): String {
        TODO("Not yet implemented")
    }

    override fun generateJwtToken(authRequest: AuthRequest): String {
        TODO("Not yet implemented")
    }
}