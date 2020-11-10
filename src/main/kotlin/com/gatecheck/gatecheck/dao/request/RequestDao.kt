package com.gatecheck.gatecheck.dao.request

import com.gatecheck.gatecheck.model.Request
import java.util.*

interface RequestDao {
    fun getRequests(user: UUID, Messages: Boolean, Amount: Int, Index: Int): Set<Request>

    fun addNewRequest(request: Request): Request

}