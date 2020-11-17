package com.gatecheck.gatecheck.security

import org.springframework.http.HttpStatus
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.server.ResponseStatusException
import java.util.*

class CurrentUser(
        val dbUser: com.gatecheck.gatecheck.model.entity.User,
        val data: Map<String, Any> = mutableMapOf(),
        extraAuthorities: List<GrantedAuthority> = mutableListOf()
) : org.springframework.security.core.userdetails.User(
        dbUser.username,
        dbUser.password,
        true,
        true,
        true,
        true,
        mutableListOf(
                GrantedAuthority { dbUser.javaClass.kotlin.simpleName },
                *extraAuthorities.toTypedArray()
        )
) {
    val authorityMap: Map<String, GrantedAuthority> = authorities.associateBy { it.authority }

    companion object {
        val isLoggedIn: Boolean
            get() {
                val principal = SecurityContextHolder.getContext().authentication.principal
                return principal !is String || principal != "anonymousUser"
            }
        val isNotLoggedIn: Boolean get() = !isLoggedIn
        val currentUser: CurrentUser
            get() = SecurityContextHolder.getContext().authentication.principal as CurrentUser
        val id get() = currentUser.dbUser.id
        val idOrError: UUID
            get() {
                if (isNotLoggedIn) throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Must be logged in!")
                return id
            }
        val isStudent: Boolean get() = currentUser.authorityMap.containsKey("Student")
        val isParent: Boolean get() = currentUser.authorityMap.containsKey("Parent")
        val isInstructor: Boolean get() = currentUser.authorityMap.containsKey("Instructor")
        val isAdmin: Boolean get() = currentUser.authorityMap.containsKey("Admin")
    }
}