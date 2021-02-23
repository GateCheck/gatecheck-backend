package com.gatecheck.gatecheck.api

import com.gatecheck.gatecheck.repository.user.UserRepository
import com.gatecheck.gatecheck.service.UserDeletionRequestService
import com.gatecheck.gatecheck.utils.Routes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(Routes.BASE + Routes.UserDeletion.BASE)
class UserDeletionController @Autowired constructor(
        private val userDeletionRequestService: UserDeletionRequestService,
        private val userRepository: UserRepository
) {
    @GetMapping
    fun deleteUser(@RequestParam(required = true) id: UUID) {
        val request = userDeletionRequestService.getRequest(id);
        if (request.isEmpty) return;
        userRepository.deleteById(request.get().userId);
        userDeletionRequestService.deleteRequest(id);
    }

    @GetMapping(Routes.UserDeletion.CANCEL)
    fun cancelDeletion(@RequestParam(required = true) id: UUID) {
        userDeletionRequestService.deleteRequest(id);
    }
}