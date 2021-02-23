package com.gatecheck.gatecheck.dao.userDeletion

import com.gatecheck.gatecheck.model.UserDeletionRequest
import java.util.*

interface UserDeletionRequestDao {
    fun getRequest(id: UUID): Optional<UserDeletionRequest>
    fun insertRequest(request: UserDeletionRequest): UserDeletionRequest
    fun deleteRequest(id: UUID)
}