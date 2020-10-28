package com.gatecheck.gatecheck.repository.inheritance

import org.bson.Document
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.domain.Example
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.repository.query.MongoEntityInformation
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository
import java.io.Serializable
import java.util.*


class InheritanceAwareMongoRepository<T, ID : Serializable>(
        private val metadata: MongoEntityInformation<T, ID>,
        private val mongoOperations: MongoOperations
) : SimpleMongoRepository<T, ID>(
        metadata,
        mongoOperations
) {
    private val classCriteriaDocument: Document
    private val classCriteria: Criteria?
    private val classCriteriaIsNull: Boolean

    init {
        if (metadata.javaType.isAnnotationPresent(TypeAlias::class.java)) {
            classCriteria = Criteria.where("_class").`is`(metadata.javaType.getAnnotation(TypeAlias::class.java).value)
            classCriteriaDocument = classCriteria.criteriaObject
            classCriteriaIsNull = false
        } else {
            classCriteria = null
            classCriteriaIsNull = true
            classCriteriaDocument = Document()
        }
    }

    override fun count(): Long {
        return if (classCriteriaIsNull) super.count() else mongoOperations.getCollection(metadata.collectionName).countDocuments(classCriteriaDocument)
    }

    override fun findAll(): MutableList<T> {
        return if (classCriteriaIsNull) super.findAll() else mongoOperations.find<T>(Query().addCriteria(classCriteria!!), metadata.javaType, metadata.collectionName)
    }

    override fun <S : T> exists(example: Example<S>): Boolean {
        val query = Query(Criteria().alike(example)).collation(metadata.collation)

        return if (classCriteriaIsNull) super.exists(example) else mongoOperations.exists(query.addCriteria(classCriteria!!), example.probeType, metadata.collectionName)
    }

    override fun existsById(id: ID): Boolean {
        return if (classCriteriaIsNull) super.existsById(id) else mongoOperations.exists(
                Query().addCriteria(Criteria.where(metadata.idAttribute).`is`(id)).addCriteria(classCriteria!!),
                metadata.javaType,
                metadata.collectionName
        )
    }

    override fun findById(id: ID): Optional<T> {
        val find = classCriteria?.let {
            mongoOperations.findOne(
                    Query().addCriteria(Criteria.where(metadata.idAttribute).`is`(id)).addCriteria(classCriteria),
                    metadata.javaType,
                    metadata.collectionName
            )
        }
        return if (find == null && classCriteriaIsNull) super.findById(id) else if (find != null) Optional.of(find) else Optional.empty()
    }

    override fun <S : T> findOne(example: Example<S>): Optional<S> {
        val find = classCriteria?.let {
            mongoOperations.findOne(
                    Query(Criteria().alike(example)).addCriteria(classCriteria),
                    example.probeType,
                    metadata.collectionName
            )
        }
        return if (find == null && classCriteriaIsNull) super.findOne(example) else if (find != null) Optional.of(find) else Optional.empty()
    }
}