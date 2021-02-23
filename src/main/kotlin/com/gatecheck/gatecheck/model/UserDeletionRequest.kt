package com.gatecheck.gatecheck.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "deleteRequests")
data class UserDeletionRequest @JsonCreator constructor(
        @Id val id: UUID,
        @JsonProperty val userId: UUID,
        @JsonProperty val creationDate: Instant? = Clock.System.now()
)