package com.gatecheck.gatecheck.dao.request

import com.gatecheck.gatecheck.model.Request
import com.gatecheck.gatecheck.model.entity.Student
import com.gatecheck.gatecheck.repository.RequestRepository
import com.gatecheck.gatecheck.security.CurrentUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import java.util.*


@Repository("requestDao")
class RequestDataAccessService @Autowired constructor(
        private val repository: RequestRepository
) : RequestDao {
    override fun getRequests(user: UUID, messages: Boolean, amount: Int, index: Int, all: Boolean): List<Request> {
        val requestsOfUser: List<Request> = repository.findAllBySender(user)
        val requests: MutableList<Request> = mutableListOf()

        val iter: IntRange = when {
            all -> 0..requestsOfUser.size
            index + amount >= requestsOfUser.size -> index..requestsOfUser.size
            else -> index..(index + amount)
        }

        for (i in iter) {
            if (messages) requestsOfUser[i].replies?.clear()
            requests.add(requestsOfUser[i])
        }

        return requests
    }

    override fun addRequest(request: Request): Request {
        request.receivers = (CurrentUser.currentUser.dbUser as Student).instructors ?: mutableSetOf()
        request.sender = CurrentUser.id
        repository.save(request)
        return request
    }
}