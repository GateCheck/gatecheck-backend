package com.gatecheck.gatecheck.repository

import com.gatecheck.gatecheck.model.Request
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface RequestRepository : MongoRepository<Request, UUID> {
    fun findAllBySender(sender: UUID): List<Request>
    fun findAllByReceiversContains(receiver: UUID): Set<Request>
}