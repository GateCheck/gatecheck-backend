package com.gatecheck.gatecheck.language

import org.springframework.stereotype.Service

@Service
class LanguageProvider {
    private val languages: MutableMap<String, Language> = mutableMapOf()

    fun addLanguage(languageCode: String) {
        languages[languageCode] = Language()
    }

    fun getLanguageCodes(): Set<String> {
        return languages.keys
    }

    fun getLanguageTranslations(languageCode: String): Map<String, String>? {
        return getLanguage(languageCode)?.getTranslations()
    }

    fun removeLanguage(languageCode: String) {
        languages.remove(languageCode)
    }

    fun getLanguage(languageCode: String): Language? {
        return languages[languageCode]
    }

    fun addTranslation(identifier: String, vararg translation: Translation) {
        addTranslation(identifier, *translation)
    }

    fun addTranslation(identifier: String, translations: Set<Translation>) {
        for (i in translations) {
            if (!languages.containsKey(i.languageCode)) {
                println("\n\nWARNING: ${i.languageCode} language does not exist. Creating it...\n\n")
                addLanguage(i.languageCode)
            }

            languages[i.languageCode]?.addTranslation(identifier, i.translation)
        }
    }
}