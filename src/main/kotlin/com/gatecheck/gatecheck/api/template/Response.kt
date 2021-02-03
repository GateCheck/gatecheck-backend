package com.gatecheck.gatecheck.api.template

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

@JsonIgnoreProperties("body", "statusCodeValue", "statusCode", "headers")
open class Response(status: HttpStatus) : ResponseEntity<Any>(status) {
    override fun getBody(): Any {
        return this
    }
}