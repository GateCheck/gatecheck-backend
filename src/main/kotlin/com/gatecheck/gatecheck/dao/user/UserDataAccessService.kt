package com.gatecheck.gatecheck.dao.user

import com.gatecheck.gatecheck.model.entity.User
import com.gatecheck.gatecheck.repository.user.InstructorRepository
import com.gatecheck.gatecheck.repository.user.ParentRepository
import com.gatecheck.gatecheck.repository.user.StudentRepository
import com.gatecheck.gatecheck.repository.user.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import java.util.*

@Repository("userDao")
class UserDataAccessService @Autowired constructor(
        private val repository: UserRepository,
        private val studentRepository: StudentRepository,
        private val instructorRepository: InstructorRepository,
        private val parentRepository: ParentRepository
) : UserDao {
    override fun getUser(): Optional<User> {
        TODO("Not yet implemented")
    }

    override fun getUser(vararg user: UUID, allUsers: Boolean): Set<User> {
        TODO("Not yet implemented")
    }

    override fun deleteUser(): User {
        TODO("Not yet implemented")
    }

    override fun updateUser(user: User): User {
        TODO("Not yet implemented")
    }

    override fun updateUser(userId: UUID, user: User): User {
        TODO("Not yet implemented")
    }
}