package com.gatecheck.gatecheck.service

import com.gatecheck.gatecheck.dao.request.RequestDao
import com.gatecheck.gatecheck.model.Request
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import java.util.*


@Service
class RequestService @Autowired construct(@Qualifier("requestDao") private val requestDao:RequestDao){
    fun GetRequests(Messages: Boolean, Amount: Int, Index:Int ):Set<Request>= requestDao.GetRequests(Messages,Amount,Index)

    fun AddNewRequest(request:Request):Request=requestDao.AddNewRequest(request)
}