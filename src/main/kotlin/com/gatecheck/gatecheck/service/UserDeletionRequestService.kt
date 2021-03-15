package com.gatecheck.gatecheck.service


import com.gatecheck.gatecheck.dao.userDeletion.UserDeletionRequestDao
import com.gatecheck.gatecheck.model.UserDeletionRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserDeletionRequestService @Autowired
constructor(@Qualifier("userDeletionRequestDao") private val UDRDao: UserDeletionRequestDao) {
    fun getRequest(id:UUID)=UDRDao.getRequest(id)
    fun insertRequest(request: UserDeletionRequest)=UDRDao.insertRequest(request)
    fun deleteRequest(id:UUID)=UDRDao.deleteRequest(id)
}