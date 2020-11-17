package com.gatecheck.gatecheck.dao.user

import com.gatecheck.gatecheck.model.entity.Instructor
import com.gatecheck.gatecheck.model.entity.Student
import com.gatecheck.gatecheck.model.entity.User
import com.gatecheck.gatecheck.repository.user.InstructorRepository
import com.gatecheck.gatecheck.repository.user.ParentRepository
import com.gatecheck.gatecheck.repository.user.StudentRepository
import com.gatecheck.gatecheck.repository.user.UserRepository
import com.gatecheck.gatecheck.security.CurrentUser
import com.gatecheck.gatecheck.service.EmailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Repository
import java.util.*

@Repository("userDao")
class UserDataAccessService @Autowired constructor(
        private val repository: UserRepository,
        private val studentRepository: StudentRepository,
        private val instructorRepository: InstructorRepository,
        private val parentRepository: ParentRepository,
        private val emailService: EmailService,
        private val mongoOperations: MongoOperations,
        private val passwordEncoder: PasswordEncoder
) : UserDao {
    override fun getUser(): Optional<User> {
        return Optional.of(CurrentUser.currentUser.dbUser)
    }

    override fun getUser(vararg user: UUID, allUsers: Boolean): Set<User> {
        if (!CurrentUser.isInstructor) return setOf(CurrentUser.currentUser.dbUser)
        val instructor = CurrentUser.currentUser.dbUser as Instructor
        if (allUsers) {
            return instructor.students?.let { studentRepository.findAllById(it).toSet() } ?: setOf()
        }

        return instructor.students?.let { instructorStudents ->
            studentRepository.findAllById(user.filter { instructorStudents.contains(it) }).toSet()
        } ?: setOf()
    }

    override fun deleteUser(): User {
        //emailService.sendEmail("oeharel@gmail.com", "hi", "bye") not working
        return CurrentUser.currentUser.dbUser
    }

    override fun <T : User> updateUser(updatedUser: T): User {
        val query = Query.query(Criteria.where("_id").`is`(updatedUser.id))
        val update = Update()
                .set("username", updatedUser.username)
                .set("email", updatedUser.email)
                .set("password", passwordEncoder.encode(updatedUser.password))
        if (CurrentUser.isInstructor && CurrentUser.isAdmin) {
            if ((updatedUser as Instructor).schools != null) update.set("school", (updatedUser as Instructor).schools)
        }

        mongoOperations.updateFirst(query, update, "users")
        return repository.findById(CurrentUser.id).get()
    }

    override fun <T : User> updateUser(userId: UUID, updatedUser: T): Optional<User> {
        if (!CurrentUser.isInstructor || !CurrentUser.isAdmin) return Optional.empty()
        if (!repository.existsById(userId)) return Optional.empty()
        val query = Query.query(Criteria.where("_id").`is`(userId))
        val update = Update()
                .set("name", updatedUser.name)
                .set("password", updatedUser.password)
        if (updatedUser is Student) {
            update.set("school", updatedUser.school)
        }

        mongoOperations.updateFirst(query, update, "users")
        return Optional.of(repository.findById(userId).get())
    }
}