package com.gatecheck.gatecheck.repository.user

import com.gatecheck.gatecheck.model.UserDeletionRequest
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface UserDeletionRequestRepository: MongoRepository<UserDeletionRequest,UUID>{}