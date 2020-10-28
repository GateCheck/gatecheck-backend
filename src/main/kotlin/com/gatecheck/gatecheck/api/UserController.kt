package com.gatecheck.gatecheck.api

import com.gatecheck.gatecheck.api.template.DefaultUserResponse
import com.gatecheck.gatecheck.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/hi")
class UserController @Autowired constructor(private val userService: UserService) {
    @GetMapping
    fun getUser(): DefaultUserResponse {
        val user = userService.getUser()
        return DefaultUserResponse(user.isPresent, user.orElse(null))
    }
}