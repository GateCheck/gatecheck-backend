package com.gatecheck.gatecheck.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import java.util.*

data class Message @JsonCreator constructor(
        @JsonProperty val sendDate: Instant = Clock.System.now(),
        @JsonProperty val sender: UUID,
        @JsonProperty val text: String
) {
    @JsonProperty("data_type") var dataType = this::class.java.simpleName
}