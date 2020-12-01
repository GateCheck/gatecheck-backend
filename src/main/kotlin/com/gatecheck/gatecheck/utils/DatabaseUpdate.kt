package com.gatecheck.gatecheck.utils

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import java.util.*

class DatabaseUpdate<T>(val dataClass: Class<T>, identifier: UUID) {
    private val update = Update()
    private val collection = dataClass.getAnnotationsByType(Document::class.java)[0].collection
    private val searchQuery = Query.query(Criteria.where("_id").`is`(identifier))
    private var dataUnset = true
    private var data: T? = null

    fun <V> addUpdateQuery(fieldName: String, value: V): DatabaseUpdate<T> {
        update.set(fieldName, value)
        return this
    }

    fun <V> addUpdateQuery(fieldName: String, value: (T?) -> V): DatabaseUpdate<T> {
        if (data == null && dataUnset) {
            data = mongoOperations.find(searchQuery, dataClass, collection)[0]
            dataUnset = false
        }

        update.set(fieldName, value(data))
        return this
    }

    fun <V> addConditionalUpdateData(fieldName: String, value: V, condition: (T?) -> Boolean): DatabaseUpdate<T> {
        if (data == null && dataUnset) {
            data = mongoOperations.find(searchQuery, dataClass, collection)[0]
            dataUnset = false
        }

        if (condition(data)) {
            update.set(fieldName, value)
        }
        return this
    }

    fun <V> addConditionalUpdate(fieldName: String, value: V, condition: () -> Boolean): DatabaseUpdate<T> {
        if (condition()) {
            update.set(fieldName, value)
        }
        return this
    }

    fun <V> addConditionalUpdate(fieldName: String, value: () -> V, condition: () -> Boolean): DatabaseUpdate<T> {
        if (condition()) {
            update.set(fieldName, value)
        }
        return this
    }

    fun apply(): T? {
        return mongoOperations.findAndModify(searchQuery, update, dataClass, collection)
    }

    companion object {
        @Autowired
        private lateinit var mongoOperations: MongoOperations
    }
}