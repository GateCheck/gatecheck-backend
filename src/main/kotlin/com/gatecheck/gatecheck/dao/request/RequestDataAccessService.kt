package com.gatecheck.gatecheck.dao.request

import com.gatecheck.gatecheck.model.Request
import com.gatecheck.gatecheck.repository.RequestRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import java.util.*


@Repository("requestDao")
class RequestDataAccessService @Autowired constructor(
        private val repository: RequestRepository
) : RequestDao {
    override fun getRequests(user: UUID, messages: Boolean, amount: Int, index: Int): List<Request> {
        val requestsOfUser: List<Request> = repository.findAllBySender(user)
        val requests: MutableList<Request> = mutableListOf()

        for (i in index..requestsOfUser.size) {
            if (messages) requestsOfUser[i].replies?.clear()
            requests.add(requestsOfUser[i])
        }

        return requests
    }

    override fun addRequest(request: Request): Request {
        repository.save(request)
        return request
    }
}