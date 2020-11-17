package com.gatecheck.gatecheck.config

import com.sun.mail.util.MailSSLSocketFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl


@Configuration
class MailConfig @Autowired constructor(
        @Value("\${mail.email}") val email: String,
        @Value("\${mail.password}") val password: String
) {
    @Bean
    fun getJavaMailSender(): JavaMailSender {
        val mailSender = JavaMailSenderImpl()
        mailSender.host = "smtp.gmail.com"
        mailSender.port = 587

        mailSender.username = email
        mailSender.password = password
        mailSender.javaMailProperties["mail.transport.protocol"] = "smtp"
        mailSender.javaMailProperties["mail.smtp.auth"] = "true"
        mailSender.javaMailProperties["mail.smtp.starttls.enable"] = "true"
        mailSender.javaMailProperties["mail.debug"] = "true"
        val sf = MailSSLSocketFactory()
        sf.isTrustAllHosts = true
        mailSender.javaMailProperties["mail.imaps.ssl.trust"] = "*"
        mailSender.javaMailProperties["mail.imaps.ssl.socketFactory"] = sf

        return mailSender
    }
}