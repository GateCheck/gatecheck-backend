package com.gatecheck.gatecheck.dao.userDeletion

import com.gatecheck.gatecheck.repository.user.UserDeletionRequestRepository
import com.gatecheck.gatecheck.dao.userDeletion.UserDeletionRequestDao
import com.gatecheck.gatecheck.model.UserDeletionRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import java.util.*


@Repository("userDeletionRequestDao")
class UserDeletionRequestDataAccessService @Autowired constructor(
        private val repository: UserDeletionRequestRepository
) : UserDeletionRequestDao {
    override fun getRequest(id: UUID): Optional<UserDeletionRequest> = repository.findById(id)
    override fun insertRequest(request: UserDeletionRequest) = repository.insert(request)
    override fun deleteRequest(id: UUID) = repository.deleteById(id)
}