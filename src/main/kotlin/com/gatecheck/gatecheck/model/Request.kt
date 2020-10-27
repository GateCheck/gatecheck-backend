package com.gatecheck.gatecheck.model

import kotlinx.datetime.Instant
import java.util.*

data class Request(
        val title: String,
        val creationDate: Instant,
        val from: Instant,
        val to: Instant,
        val destination: String,
        val details: String,
        val status: RequestStatus,
        val replies: List<Message>,
        val sender: UUID,
        val receivers: Set<UUID>
)