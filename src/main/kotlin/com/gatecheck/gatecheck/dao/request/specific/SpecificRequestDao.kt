package com.gatecheck.gatecheck.dao.request.specific

import com.gatecheck.gatecheck.model.Message
import com.gatecheck.gatecheck.model.Request
import com.gatecheck.gatecheck.model.RequestStatus
import java.util.*

interface SpecificRequestDao {
    fun getRequest(requestId: UUID): Request?

    fun deleteRequest(requestId: UUID): Request?

    fun updateStatus(requestId: UUID, requestStatus: RequestStatus): Request?

    fun addMessage(requestId: UUID, message: Message): Request?

    fun deleteMessage(requestId: UUID, messageIndex: Int): Request?
}