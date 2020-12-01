package com.gatecheck.gatecheck.service

import com.gatecheck.gatecheck.dao.request.specific.SpecificRequestDao
import com.gatecheck.gatecheck.model.Message
import com.gatecheck.gatecheck.model.Request
import com.gatecheck.gatecheck.model.RequestStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import java.util.*

@Service
class SpecificRequestService @Autowired constructor(
        @Qualifier("specificRequestDao") private val specificRequestDao: SpecificRequestDao
) {
    fun getRequest(requestId: UUID): Request? = specificRequestDao.getRequest(requestId)

    fun deleteRequest(requestId: UUID): Request? = specificRequestDao.deleteRequest(requestId)

    fun updateStatus(requestId: UUID, requestStatus: RequestStatus): Request? = specificRequestDao.updateStatus(requestId, requestStatus)

    fun addMessage(requestId: UUID, message: Message): Request? = specificRequestDao.addMessage(requestId, message)

    fun deleteMessage(requestId: UUID, messageIndex: Int): Request? = specificRequestDao.deleteMessage(requestId, messageIndex)
}