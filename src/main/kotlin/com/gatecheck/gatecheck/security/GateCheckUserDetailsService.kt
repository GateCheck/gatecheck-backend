package com.gatecheck.gatecheck.security

import com.gatecheck.gatecheck.model.entity.User
import com.gatecheck.gatecheck.repository.user.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class GateCheckUserDetailsService @Autowired constructor(
        private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails? {
        val user: User = userRepository.findByUsername(username) ?: return null
        return CurrentUser(user)
    }
}