package com.gatecheck.gatecheck.api

import com.gatecheck.gatecheck.api.template.DefaultUserResponse
import com.gatecheck.gatecheck.api.template.UserUpdate
import com.gatecheck.gatecheck.service.UserService
import com.gatecheck.gatecheck.utils.Routes
import org.jetbrains.annotations.NotNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(Routes.BASE + Routes.User.BASE)
class UserController @Autowired constructor(private val userService: UserService) {
    @GetMapping
    fun getUser(@RequestParam(required = false) users: Array<UUID>?, @RequestParam(required = false) allUsers: Boolean?): DefaultUserResponse {
        return if (users == null && allUsers == null) {
            val user = userService.getUser()
            if(user.isPresent){
                val u=user.get();
                u.password="";
                DefaultUserResponse(true,u);
            }
            DefaultUserResponse(false,null);
        } else {
            DefaultUserResponse(true, users = userService.getUser(*users ?: arrayOf(), allUsers = allUsers ?: false))
        }
    }

    @DeleteMapping
    fun deleteUser(): DefaultUserResponse {
        return DefaultUserResponse(true, userService.deleteUser())
    }

    @PutMapping
    fun updateUser(@RequestBody @NotNull @Validated user: UserUpdate): DefaultUserResponse {
        return DefaultUserResponse(true, userService.updateUser(user))
    }

    @PutMapping(Routes.User.SINGLE_USER_SELECT)
    fun updateUser(@PathVariable userId: UUID, @RequestBody @NotNull @Validated user: UserUpdate): DefaultUserResponse {
        val update = userService.updateUser(userId, user)
        return DefaultUserResponse(update != null, update)
    }
}