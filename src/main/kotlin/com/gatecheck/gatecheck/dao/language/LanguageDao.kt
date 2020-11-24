package com.gatecheck.gatecheck.dao.language

interface LanguageDao {
    fun getLanguages(): Set<String>

    fun getLanguage(languageCode: String): Map<String, String>
}