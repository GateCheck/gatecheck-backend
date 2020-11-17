package com.gatecheck.gatecheck.dao.email

interface EmailDao {
    fun sendEmail(to: String, title: String, text: String)
}