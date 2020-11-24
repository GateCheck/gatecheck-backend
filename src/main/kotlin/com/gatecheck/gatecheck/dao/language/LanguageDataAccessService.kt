package com.gatecheck.gatecheck.dao.language

import org.springframework.stereotype.Repository

@Repository("languageDao")
class LanguageDataAccessService : LanguageDao {
    override fun getLanguages(): Set<String> {
        TODO("Not yet implemented")
    }

    override fun getLanguage(languageCode: String): Map<String, String> {
        TODO("Not yet implemented")
    }
}