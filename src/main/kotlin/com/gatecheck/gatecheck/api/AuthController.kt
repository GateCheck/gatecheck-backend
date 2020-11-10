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
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping(Routes.BASE + Routes.Auth.BASE)
class AuthController @Autowired constructor(private val authService: AuthService) {
    @PostMapping(Routes.Auth.LOGIN)
    fun login(@RequestBody @Validated @NotNull authRequest: AuthRequest): Response {
        return try {
            AuthResponse(authService.login(authRequest), true)
        } catch (e: ResponseStatusException) {
            SuccessResponse(false, e.status.value())
        }
    }

    @PostMapping(Routes.Auth.REGISTER)
    fun register(@RequestBody @Validated @NotNull user: Student): Response {
        return try {
            AuthResponse(authService.register(user), true)
        } catch (e: ResponseStatusException) {
            SuccessResponse(false, e.status.value(), e.reason)
        } catch (e: Exception) {
            SuccessResponse(false, 401, if (e.message?.contains("username") == true) "Username already exists!" else "Email already exists!")
        }
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    fun missingFieldsException(ex: HttpMessageNotReadableException): SuccessResponse {
        return SuccessResponse(false, 400, "Missing field '" + ex.message!!.substring(
                ex.message!!.indexOf("value failed for JSON property ") + "value failed for JSON property ".length,
                ex.message!!.indexOf("due to missing (therefore NULL) value") - 1
        ) + "' - This field is required.")
    }
}