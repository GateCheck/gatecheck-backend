package com.gatecheck.gatecheck

import com.gatecheck.gatecheck.utils.Routes
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import com.gatecheck.gatecheck.model.entity.*
import org.springframework.boot.test.web.client.postForObject
import java.util.*

@SpringBootTest
class GateCheckApplicationTests {
	val testRestTemplate: TestRestTemplate= TestRestTemplate(TestRestTemplate.HttpClientOption.ENABLE_COOKIES);
	@Test
	fun registerUser() {
		val user=Student(UUID.randomUUID(),
				"name",
				"username",
				"Example@gmail.com",
				"P4ssword",
				"/profile/username",
				"he",
				null,
				null,
				"school");
		println(user);
		val response=testRestTemplate.postForObject("http://"+Routes.HOST+Routes.BASE + Routes.Auth.BASE+Routes.Auth.REGISTER,user, Any::class.java);
		println(response);
	}

}
