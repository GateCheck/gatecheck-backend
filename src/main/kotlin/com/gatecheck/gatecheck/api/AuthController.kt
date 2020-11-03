package com.gatecheck.gatecheck.api

import com.gatecheck.gatecheck.service.AuthService
import com.gatecheck.gatecheck.utils.Routes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Routes.Auth.BASE)
class AuthController @Autowired constructor(private val authService: AuthService) {
    @PostMapping
    fun login() {

    }

    @PostMapping(Routes.Auth.REGISTER)
    fun register() {

    }
}