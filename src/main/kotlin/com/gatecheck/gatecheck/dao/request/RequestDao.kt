package com.gatecheck.gatecheck.dao.request

import com.gatecheck.gatecheck.model.Request
import java.util.*

interface RequestDao {
    fun getRequests(user: UUID, messages: Boolean, amount: Int, index: Int): List<Request>

    fun addRequest(request: Request): Request
}