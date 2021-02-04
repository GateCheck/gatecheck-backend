package com.gatecheck.gatecheck.service

import com.gatecheck.gatecheck.dao.request.RequestDao
import com.gatecheck.gatecheck.model.Request
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import java.util.*


@Service
class RequestService @Autowired constructor(@Qualifier("requestDao") private val requestDao: RequestDao) {
    fun getRequests(user: UUID, messages: Boolean, amount: Int, index: Int, all: Boolean): List<Request>
            = requestDao.getRequests(user, messages, amount, index, all)

    fun addRequest(request: Request): Request = requestDao.addRequest(request)
}