package com.gatecheck.gatecheck.api

import com.gatecheck.gatecheck.api.template.DefaultRequestResponse
import com.gatecheck.gatecheck.api.template.MessagePostRequest
import com.gatecheck.gatecheck.model.Message
import com.gatecheck.gatecheck.model.RequestStatus
import com.gatecheck.gatecheck.security.CurrentUser
import com.gatecheck.gatecheck.service.SpecificRequestService
import com.gatecheck.gatecheck.utils.Routes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(Routes.BASE + Routes.Request.BASE + Routes.Request.SINGLE_REQUEST_SELECT)
class SpecificRequestController @Autowired constructor(
        private val specificRequestService: SpecificRequestService
) {
    @GetMapping
    fun getRequest(@PathVariable("requestId") requestId: UUID): DefaultRequestResponse {
        val request = specificRequestService.getRequest(requestId)
        return DefaultRequestResponse(request != null, request)
    }


    @DeleteMapping
    fun deleteRequest(@PathVariable("requestId") requestId: UUID): DefaultRequestResponse {
        val request = specificRequestService.deleteRequest(requestId)
        return DefaultRequestResponse(request != null, request)
    }

    @PostMapping(Routes.Request.STATUS)
    fun updateRequestStatus(@PathVariable("requestId") requestId: UUID, @PathVariable("statusType") statusType: RequestStatus): DefaultRequestResponse {
        val request = specificRequestService.updateStatus(requestId, statusType)
        return DefaultRequestResponse(request != null, request)
    }

    @PostMapping
    fun addMessage(@PathVariable("requestId") requestId: UUID, @RequestBody @Validated messagePostRequest: MessagePostRequest): DefaultRequestResponse {
        val request = specificRequestService.addMessage(requestId, Message(sender = CurrentUser.id, text = messagePostRequest.text))
        return DefaultRequestResponse(request != null, request)
    }

    @DeleteMapping(Routes.Request.SPECIFIC_MESSAGE)
    fun deleteMessage(@PathVariable("requestId") requestId: UUID, @PathVariable("messageIndex") messageIndex: Int): DefaultRequestResponse {
        val request = specificRequestService.deleteMessage(requestId, messageIndex)
        return DefaultRequestResponse(request != null, request)
    }
}