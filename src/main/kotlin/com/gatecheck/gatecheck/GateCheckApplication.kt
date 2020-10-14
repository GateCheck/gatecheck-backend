package com.gatecheck.gatecheck

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GateCheckApplication

fun main(args: Array<String>) {
	runApplication<GateCheckApplication>(*args)
}
