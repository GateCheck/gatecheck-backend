package com.gatecheck.gatecheck.dao.email

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Repository

@Repository("emailDao")
class EmailDataAccessService @Autowired constructor(
        private val emailSender: JavaMailSender
) : EmailDao {
    override fun sendEmail(to: String, title: String, text: String, isHtml: Boolean) {
        val message = emailSender.createMimeMessage()
        val helper: MimeMessageHelper = MimeMessageHelper(message)
        helper.setFrom("gatecheckil@gmail.com")
        helper.setTo(to)
        helper.setSubject(title)
        helper.setText(text, isHtml)
        emailSender.send(message)
    }

}