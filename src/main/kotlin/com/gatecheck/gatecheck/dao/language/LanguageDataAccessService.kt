package com.gatecheck.gatecheck.dao.language

import com.gatecheck.gatecheck.language.LanguageProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository("languageDao")
class LanguageDataAccessService @Autowired constructor(
        private val languageProvider: LanguageProvider
) : LanguageDao {
    override fun getLanguages(): Set<String> {
        return languageProvider.getLanguageCodes()
    }

    override fun getLanguage(languageCode: String): Map<String, String>? {
        return languageProvider.getLanguageTranslations(languageCode)
    }
}