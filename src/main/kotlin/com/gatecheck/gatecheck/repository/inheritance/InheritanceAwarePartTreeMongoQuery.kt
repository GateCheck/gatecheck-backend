package com.gatecheck.gatecheck.repository.inheritance

import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.repository.query.ConvertingParameterAccessor
import org.springframework.data.mongodb.repository.query.MongoQueryMethod
import org.springframework.data.mongodb.repository.query.PartTreeMongoQuery
import org.springframework.data.repository.query.QueryMethodEvaluationContextProvider
import org.springframework.expression.spel.standard.SpelExpressionParser

/**
 * to allow for special name methods in repositories
 */
class InheritanceAwarePartTreeMongoQuery(
        method: MongoQueryMethod,
        mongoOperations: MongoOperations,
        expressionParser: SpelExpressionParser,
        evaluationContextProvider: QueryMethodEvaluationContextProvider
) : PartTreeMongoQuery(
        method,
        mongoOperations,
        expressionParser,
        evaluationContextProvider
) {
    private val inheritanceCriteria: Criteria? =
            if (method.entityInformation.javaType.isAnnotationPresent(TypeAlias::class.java))
                Criteria.where("_class").`is`(method.entityInformation.javaType.getAnnotation(TypeAlias::class.java).value)
            else null

    override fun createQuery(accessor: ConvertingParameterAccessor): Query {
        val query = super.createQuery(accessor)
        return inheritanceCriteria?.let { query.addCriteria(inheritanceCriteria) } ?: query
    }

    override fun createCountQuery(accessor: ConvertingParameterAccessor): Query {
        val query = super.createCountQuery(accessor)
        return inheritanceCriteria?.let { query.addCriteria(inheritanceCriteria) } ?: query
    }
}