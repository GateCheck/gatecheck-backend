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
        val currentUserId get() = currentUser.dbUser.id
        val currentUserIdOrError: UUID
            get() {
                if (isNotLoggedIn) throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Must be logged in!")
                return currentUserId
            }
        val currentUserIsStudent: Boolean get() = currentUser.authorityMap.containsKey("Student")
        val currentUserIsParent: Boolean get() = currentUser.authorityMap.containsKey("Parent")
        val currentUserIsInstructor: Boolean get() = currentUser.authorityMap.containsKey("Instructor")
    }
}