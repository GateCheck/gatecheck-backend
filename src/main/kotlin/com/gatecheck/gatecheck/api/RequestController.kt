package com.gatecheck.gatecheck.api

import com.gatecheck.gatecheck.api.template.DefaultRequestResponse
import com.gatecheck.gatecheck.model.Request
import com.gatecheck.gatecheck.security.CurrentUser
import com.gatecheck.gatecheck.service.RequestService
import org.jetbrains.annotations.NotNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/request")
class RequestController @Autowired constructor(private val requestService: RequestService) {
    @GetMapping
    fun getRequests(
            @RequestParam(required = false, defaultValue = "false") messages: Boolean = false,
            @RequestParam(required = false, defaultValue = "10") amount: Int = 10,
            @RequestParam(required = false, defaultValue = "0") index: Int = 0
    ): DefaultRequestResponse {
        return DefaultRequestResponse(true, requests = requestService.getRequests(CurrentUser.id, messages, amount, index))
    }

    @PostMapping
    fun addRequest(@NotNull @Validated @RequestBody request: Request): DefaultRequestResponse {
        request.sender = CurrentUser.id
        return DefaultRequestResponse(true, requestService.addRequest(request))
    }
}