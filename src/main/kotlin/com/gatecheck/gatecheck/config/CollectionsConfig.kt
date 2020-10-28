package com.gatecheck.gatecheck.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.index.Index
import javax.annotation.PostConstruct

@Configuration
@DependsOn("mongoTemplate")
class CollectionsConfig {
    @Autowired
    lateinit var mongoTemplate: MongoTemplate

    @PostConstruct
    fun initIndexes() {
        mongoTemplate.indexOps("users")
                .ensureIndex(Index().on("username", Sort.Direction.ASC).unique())
        mongoTemplate.indexOps("users")
                .ensureIndex(Index().on("email", Sort.Direction.ASC).unique())
        mongoTemplate.indexOps("users")
                .ensureIndex(Index().on("profilePath", Sort.Direction.ASC).unique())
    }
}