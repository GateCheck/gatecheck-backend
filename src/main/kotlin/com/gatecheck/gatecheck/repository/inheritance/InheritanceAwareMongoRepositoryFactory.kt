package com.gatecheck.gatecheck.repository.inheritance

import org.springframework.data.mapping.context.MappingContext
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.mapping.MongoPersistentEntity
import org.springframework.data.mongodb.core.mapping.MongoPersistentProperty
import org.springframework.data.mongodb.repository.query.MongoQueryMethod
import org.springframework.data.mongodb.repository.query.StringBasedMongoQuery
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory
import org.springframework.data.projection.ProjectionFactory
import org.springframework.data.repository.core.NamedQueries
import org.springframework.data.repository.core.RepositoryMetadata
import org.springframework.data.repository.query.QueryLookupStrategy
import org.springframework.data.repository.query.QueryMethodEvaluationContextProvider
import org.springframework.data.repository.query.RepositoryQuery
import org.springframework.expression.spel.standard.SpelExpressionParser
import java.lang.reflect.Method
import java.util.*


/**
 * to register our PartTreeMongoQuery
 */
class InheritanceAwareMongoRepositoryFactory(private val mongoOperations: MongoOperations) : MongoRepositoryFactory(mongoOperations) {
    companion object {
        private val EXPRESSION_PARSER = SpelExpressionParser()
    }

    override fun getQueryLookupStrategy(key: QueryLookupStrategy.Key?, evaluationContextProvider: QueryMethodEvaluationContextProvider): Optional<QueryLookupStrategy> {
        return Optional.of(MongoQueryLookupStrategy(mongoOperations, evaluationContextProvider, mongoOperations.converter.mappingContext))
    }

    /**
     * Taken from the Spring Data for MongoDB source code and modified to return InheritanceAwarePartTreeMongoQuery
     * instead of PartTreeMongoQuery. It's a static private part so copy/paste was the only way...
     */
    class MongoQueryLookupStrategy(
            private val operations: MongoOperations,
            private val evaluationContextProvider: QueryMethodEvaluationContextProvider,
            var mappingContext: MappingContext<out MongoPersistentEntity<*>, MongoPersistentProperty>
    ) : QueryLookupStrategy {
        override fun resolveQuery(
                method: Method,
                metadata: RepositoryMetadata,
                factory: ProjectionFactory,
                namedQueries: NamedQueries
        ): RepositoryQuery {
            val queryMethod = MongoQueryMethod(method, metadata, factory, mappingContext)
            val namedQueryName = queryMethod.namedQueryName

            return when {
                namedQueries.hasQuery(namedQueryName) ->
                    StringBasedMongoQuery(namedQueries.getQuery(namedQueryName), queryMethod, operations, EXPRESSION_PARSER, evaluationContextProvider)

                queryMethod.hasAnnotatedQuery() ->
                    StringBasedMongoQuery(queryMethod, operations, EXPRESSION_PARSER, evaluationContextProvider)

                else ->
                    InheritanceAwarePartTreeMongoQuery(queryMethod, operations, EXPRESSION_PARSER, evaluationContextProvider)

            }
        }

    }

}
