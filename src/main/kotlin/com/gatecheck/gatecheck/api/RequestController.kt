package com.gatecheck.gatecheck.api

import com.gatecheck.gatecheck.model.entity.User
import com.gatecheck.gatecheck.service.UserService
import com.gatecheck.gatecheck.api.template.DefaultRequestResponse
import com.gatecheck.gatecheck.model.Request
import com.gatecheck.gatecheck.service.RequestService
import org.jetbrains.annotations.NotNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/request")
class RequestController @Autowired constructor(private val requestService: RequestService, private val userService: UserService) {
    @GetMapping
    fun getRequests(@RequestParam(required = false) messages: Boolean = false,
                    @RequestParam(required = false) amount: Int = 10,
                    @RequestParam(required = false) index: Int = 0): DefaultRequestResponse {
        val user: Optional<User> = userService.getUser()

        if (user.isPresent) {
            return DefaultRequestResponse(true, null, requestService.getRequests(user.get().id, messages, amount, index))
        }
        TODO("not implemented yet")
    }

    @PostMapping
    fun addRequest(@RequestBody request: Request): DefaultRequestResponse {
        val user: Optional<User> = userService.getUser()
        if (user.isPresent) {
            request.sender = user.get().id
            return DefaultRequestResponse(true, requestService.addNewRequest(request))
        }
        TODO("not yet implemented")
    }
}