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
        @Id val id: UUID = UUID.randomUUID(),
        @JsonProperty val title: String,
        @JsonProperty val creationDate: Instant? = Clock.System.now(),
        @JsonProperty val fromDate: Instant,
        @JsonProperty val toDate: Instant,
        @JsonProperty val destination: String,
        @JsonProperty val details: String,
        @JsonProperty val status: RequestStatus? = RequestStatus.Pending,
        @JsonProperty val replies: MutableList<Message>? = mutableListOf(),
        @JsonProperty var sender: UUID,
        @JsonProperty var receivers: Set<UUID>
) {
    @JsonProperty("data_type") var dataType = this::class.java.simpleName
}