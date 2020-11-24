package com.gatecheck.gatecheck.language

class Language {
    private val translations: MutableMap<String, String> = mutableMapOf()

    fun getTranslations(): Map<String, String> {
        return translations
    }

    fun getTranslation(identifier: String): String? {
        return translations[identifier]
    }

    fun replaceTranslation(identifier: String, translation: String) {
        translations[identifier] = translation
    }

    fun removeTranslation(identifier: String) {
        translations.remove(identifier)
    }

    fun addTranslation(identifier: String, translation: String): Boolean {
        if (translations.containsKey(identifier)) return false
        translations[identifier] = translation
        return true
    }
}