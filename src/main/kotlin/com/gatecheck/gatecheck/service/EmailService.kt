package com.gatecheck.gatecheck.service

import com.gatecheck.gatecheck.dao.email.EmailDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class EmailService @Autowired constructor(
        @Qualifier("emailDao") private val emailDao: EmailDao
) {
    fun sendEmail(to: String, title: String, text: String, isHtml: Boolean = false) = emailDao.sendEmail(to, title, text, isHtml)
}