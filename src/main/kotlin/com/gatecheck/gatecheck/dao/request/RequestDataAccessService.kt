package com.gatecheck.gatecheck.dao.request

import com.gatecheck.gatecheck.model.Request
import com.gatecheck.gatecheck.repository.RequestRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import java.util.*


@Repository("requestDAO")
class RequestDataAccessService @Autowired constructor(
    private val repository:RequestRepository
): RequestDao{
    override fun GetRequests(user:UUID,Messages: Boolean, Amount: Int, Index:Int ):Set<Request>{
        TODO("Not yet implemented")
    }
    override fun AddNewRequest(request:Request):Request{
        TODO("Not yet implemented")
    }
}