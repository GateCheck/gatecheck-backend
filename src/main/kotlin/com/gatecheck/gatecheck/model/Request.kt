package com.gatecheck.gatecheck.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "requests")
data class Request @JsonCreator constructor(
        @Id val id: UUID,
        @JsonProperty val title: String,
        @JsonProperty val creationDate: Instant? = Clock.System.now(),
        @JsonProperty val from: Instant,
        @JsonProperty val to: Instant,
        @JsonProperty val destination: String,
        @JsonProperty val details: String,
        @JsonProperty val status: RequestStatus? = RequestStatus.Pending,
        @JsonProperty val replies: List<Message>? = listOf(),
        @JsonProperty val sender: UUID,
        @JsonProperty val receivers: Set<UUID>
)