package com.gatecheck.gatecheck.dao.request.specific

import com.gatecheck.gatecheck.model.Message
import com.gatecheck.gatecheck.model.Request
import com.gatecheck.gatecheck.model.RequestStatus
import com.gatecheck.gatecheck.repository.RequestRepository
import com.gatecheck.gatecheck.security.CurrentUser
import com.gatecheck.gatecheck.utils.DatabaseUpdate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import java.util.*

@Repository("specificRequestDao")
class SpecificRequestDataAccessService @Autowired constructor(
        private val requestRepository: RequestRepository
) : SpecificRequestDao {
    override fun getRequest(requestId: UUID): Request? {
        val request: Request? = requestRepository.findById(requestId).orElse(null)
        return request?.let { if (it.receivers.contains(CurrentUser.id) || it.sender == CurrentUser.id) it else null }
    }

    override fun deleteRequest(requestId: UUID): Request? {
        val request: Request? = requestRepository.findById(requestId).orElse(null)
        request?.let {
            if (it.receivers.contains(CurrentUser.id) || it.sender == CurrentUser.id)
                requestRepository.deleteById(requestId)
        }
        return request
    }

    override fun updateStatus(requestId: UUID, requestStatus: RequestStatus): Request? {
        val databaseUpdate = DatabaseUpdate(Request::class.java, requestId)
        return databaseUpdate.addUpdateQuery("status", requestStatus).apply()
    }

    override fun addMessage(requestId: UUID, message: Message): Request? {
        return messageUpdateOperation(requestId) {
            it?.replies?.add(message)
        }
    }

    override fun deleteMessage(requestId: UUID, messageIndex: Int): Request? {
        return messageUpdateOperation(requestId) {
            it?.replies?.removeAt(messageIndex)
        }
    }

    private fun messageUpdateOperation(requestId: UUID, operation: (Request?) -> Unit): Request? {
        val databaseUpdate = DatabaseUpdate(Request::class.java, requestId)

        return databaseUpdate.addUpdateQuery("replies") {
            operation(it)
            it?.replies
        }.apply()
    }

}