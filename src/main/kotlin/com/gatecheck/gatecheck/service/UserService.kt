package com.gatecheck.gatecheck.service

import com.gatecheck.gatecheck.dao.user.UserDao
import com.gatecheck.gatecheck.model.entity.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService @Autowired constructor(@Qualifier("userDao") private val userDao: UserDao) {
    fun getUser(): Optional<User> = userDao.getUser()

    fun getUser(vararg user: UUID, allUsers: Boolean): Set<User> = userDao.getUser(*user, allUsers = allUsers)

    fun deleteUser(): User = userDao.deleteUser()

    fun updateUser(updatedUser: User): User = userDao.updateUser(updatedUser)

    fun updateUser(userId: UUID, updatedUser: User): User = userDao.updateUser(userId, updatedUser)
}