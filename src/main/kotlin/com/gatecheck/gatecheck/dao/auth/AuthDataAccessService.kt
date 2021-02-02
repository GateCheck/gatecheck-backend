package com.gatecheck.gatecheck.dao.auth

import com.gatecheck.gatecheck.api.template.AuthRequest
import com.gatecheck.gatecheck.model.entity.User
import com.gatecheck.gatecheck.repository.user.UserRepository
import com.gatecheck.gatecheck.security.JwtHelper
import com.gatecheck.gatecheck.utils.Validator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Repository
import org.springframework.web.server.ResponseStatusException

@Repository("authDao")
class AuthDataAccessService @Autowired constructor(
        private val userRepository: UserRepository,
        private val jwtHelper: JwtHelper,
        private val authManager: AuthenticationManager,
        private val passwordEncoder: PasswordEncoder,
        private val validator: Validator
) : AuthDao {
    @Throws(ResponseStatusException::class)
    override fun login(authRequest: AuthRequest): String {
        return generateJwtToken(authRequest)
    }

    @Throws(ResponseStatusException::class)
    override fun <T : User> register(user: T): String {
        val password = user.password
        if (!validator.isValidEmail(user.email)) {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email!")
        } else if (user.username.length < 3 || user.username.length > 16) {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username length!")
        } else if (user.password.length < 8 || user.password.length > 32) {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid password length!")
        }

        user.profilePath = "/profile/${user.username}"
        user.password = passwordEncoder.encode(user.password)
        userRepository.insert(user)
        return generateJwtToken(AuthRequest(user.username, password, true))
    }

    @Throws(ResponseStatusException::class)
    override fun generateJwtToken(authRequest: AuthRequest): String {
        try {
            authManager.authenticate(UsernamePasswordAuthenticationToken(authRequest.username, authRequest.password))
        } catch (e: AuthenticationException) {
            e.printStackTrace()
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password!")
        }

        return jwtHelper.generateToken(authRequest.username, if (authRequest.stayLoggedIn == true) 1000000 else 48)
    }
}