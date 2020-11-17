package com.gatecheck.gatecheck.dao.email

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Repository

@Repository("emailDao")
class EmailDataAccessService @Autowired constructor(
        private val emailSender: JavaMailSender
) : EmailDao {
    override fun sendEmail(to: String, title: String, text: String) {
        val message = SimpleMailMessage()
        message.setFrom("gatecheckil@gmail.com")
        message.setTo(to)
        message.setSubject(title)
        message.setText(text)
        emailSender.send(message)
    }

}