package com.gatecheck.gatecheck

import com.gatecheck.gatecheck.repository.inheritance.InheritanceAwareMongoRepository
import com.gatecheck.gatecheck.repository.inheritance.InheritanceAwareMongoRepositoryFactoryBean
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication(scanBasePackages = [
    "com.gatecheck.gatecheck.repository.inheritance", // add bean scanning for package
    "com.gatecheck.gatecheck.api",
    "com.gatecheck.gatecheck.dao",
    "com.gatecheck.gatecheck.config",
    "com.gatecheck.gatecheck.service",
    "com.gatecheck.gatecheck.security"
])
@EnableMongoRepositories(repositoryBaseClass = InheritanceAwareMongoRepository::class,
        repositoryFactoryBeanClass = InheritanceAwareMongoRepositoryFactoryBean::class)
class GateCheckApplication

fun main(args: Array<String>) {
    runApplication<GateCheckApplication>(*args)
}
