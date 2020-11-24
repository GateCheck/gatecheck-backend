package com.gatecheck.gatecheck.service

import com.gatecheck.gatecheck.dao.language.LanguageDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class LanguageService @Autowired constructor(@Qualifier("languageDao") private val languageDao: LanguageDao) {
    fun getLanguages(): Set<String> = languageDao.getLanguages()

    fun getLanguage(languageCode: String): Map<String, String> = languageDao.getLanguage(languageCode)
}