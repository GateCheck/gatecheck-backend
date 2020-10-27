package com.gatecheck.gatecheck.model

import kotlinx.datetime.Instant
import java.util.*

data class Message(val sendDate: Instant, val sender: UUID, val text: String)