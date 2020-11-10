package com.gatecheck.gatecheck.api

import com.gatecheck.gatecheck.api.template.AuthRequest
import com.gatecheck.gatecheck.api.template.AuthResponse
import com.gatecheck.gatecheck.api.template.Response
import com.gatecheck.gatecheck.api.template.SuccessResponse
import com.gatecheck.gatecheck.model.entity.Student
import com.gatecheck.gatecheck.service.AuthService
import com.gatecheck.gatecheck.utils.Routes
import org.jetbrains.annotations.NotNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping(Routes.BASE + Routes.Auth.BASE)
class AuthController @Autowired constructor(private val authService: AuthService) {
    @ExceptionHandler
    @PostMapping(Routes.Auth.LOGIN)
    fun login(@RequestBody @Validated @NotNull authRequest: AuthRequest): Response {
        return try {
            AuthResponse(authService.login(authRequest), true)
        } catch (e: ResponseStatusException) {
            SuccessResponse(false, e.status.value())
        }
    }

    @ExceptionHandler
    @PostMapping(Routes.Auth.REGISTER)
    fun register(@RequestBody @Validated @NotNull user: Student): Response {
        return try {
            AuthResponse(authService.register(user), true)
        } catch (e: ResponseStatusException) {
            SuccessResponse(false, e.status.value(), "REQUIRED: username, password, name, email, school. Password must be between 8 and 32 characters. Email must be valid. Username must be between 3 and 16 characters.")
        } catch (e: Exception) {
            SuccessResponse(false, 401, if (e.message?.contains("username") == true) "Username already exists!" else "Email already exists!")
        }
    }
}