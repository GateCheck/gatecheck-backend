package com.gatecheck.gatecheck.api

import com.gatecheck.gatecheck.api.template.AuthRequest
import com.gatecheck.gatecheck.api.template.AuthResponse
import com.gatecheck.gatecheck.model.entity.User
import com.gatecheck.gatecheck.service.AuthService
import com.gatecheck.gatecheck.utils.Routes
import org.jetbrains.annotations.NotNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Routes.BASE + Routes.Auth.BASE)
class AuthController @Autowired constructor(private val authService: AuthService) {
    @PostMapping(Routes.Auth.LOGIN)
    fun login(@RequestBody @Validated @NotNull authRequest: AuthRequest): AuthResponse {
        return AuthResponse(authService.login(authRequest), true)
    }

    @PostMapping(Routes.Auth.REGISTER)
    fun register(@RequestBody @Validated @NotNull user: User): AuthResponse {
        return AuthResponse(authService.register(user), true)
    }
}