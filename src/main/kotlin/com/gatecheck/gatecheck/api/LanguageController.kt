package com.gatecheck.gatecheck.api

import com.gatecheck.gatecheck.api.template.DefaultLanguageResponse
import com.gatecheck.gatecheck.api.template.SuccessResponse
import com.gatecheck.gatecheck.service.LanguageService
import com.gatecheck.gatecheck.utils.Routes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Routes.BASE + Routes.Assets.LANGUAGE)
class LanguageController @Autowired constructor(private val languageService: LanguageService) {
    @GetMapping
    fun getLanguages(): DefaultLanguageResponse {
        return DefaultLanguageResponse(true, languageCodes = languageService.getLanguages())
    }

    @GetMapping(Routes.Assets.SINGLE_LANGUAGE_SELECT)
    fun getLanguage(@PathVariable("language_code") languageCode: String): DefaultLanguageResponse {
        val language = languageService.getLanguage(languageCode)
        return DefaultLanguageResponse(language != null, language)
    }
}