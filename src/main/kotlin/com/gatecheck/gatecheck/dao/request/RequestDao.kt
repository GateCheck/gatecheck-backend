package com.gatecheck.gatecheck.dao.request

import com.gatecheck.gatecheck.model.entity.Request
import java.util.*

interface RequestDao{
    fun GetRequests(Messages: Boolean, Amount: Int, Index:Int ):Set<Request>

    fun AddNewRequest(request:Request):Request
    
}