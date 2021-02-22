package com.gatecheck.gatecheck.dao.user

import com.gatecheck.gatecheck.service.UserDeletionRequestService
import com.gatecheck.gatecheck.api.template.UserUpdate
import com.gatecheck.gatecheck.model.UserDeletionRequest
import com.gatecheck.gatecheck.model.entity.Instructor
import com.gatecheck.gatecheck.model.entity.User
import com.gatecheck.gatecheck.repository.user.*
import com.gatecheck.gatecheck.security.CurrentUser
import com.gatecheck.gatecheck.service.EmailService
import com.gatecheck.gatecheck.utils.DatabaseUpdate
import com.gatecheck.gatecheck.utils.Routes
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
        private val userDeletionRequestService: UserDeletionRequestService,
        private val emailService: EmailService,
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
        val user = CurrentUser.currentUser.dbUser;
        val id: UUID = UUID.randomUUID();

        userDeletionRequestService.insertRequest(UserDeletionRequest(id, user.id));

        val deleteUrl: String = Routes.HOST + Routes.BASE + Routes.UserDeletion + "?id=" + id;
        val cancelUrl: String = Routes.HOST + Routes.BASE + Routes.UserDeletion + Routes.UserDeletion.CANCEL + "?id=" + id;
        val emailBody: String = "To delete account: " + deleteUrl + "\r\nTo cancel deletion: " + cancelUrl;
        emailService.sendEmail(user.email, "user deletion", emailBody);
        return CurrentUser.currentUser.dbUser
    }

    override fun updateUser(updatedUser: UserUpdate): User? {
        val databaseUpdate = DatabaseUpdate(User::class.java, CurrentUser.id)
        val dbUser = CurrentUser.currentUser.dbUser

        return databaseUpdate
                .addUpdateQuery("username", updatedUser.username ?: dbUser.username)
                .addUpdateQuery("language", updatedUser.language ?: dbUser.language)
                .addUpdateQuery("email", updatedUser.email ?: dbUser.email)
                .addConditionalUpdate("password", passwordEncoder.encode(updatedUser.password)) {
                    updatedUser.password != null
                }.addConditionalUpdate("school", updatedUser.school) {
                    CurrentUser.isInstructor
                            && CurrentUser.isAdmin
                            && updatedUser.school != null
                }.apply()
    }

    override fun updateUser(userId: UUID, updatedUser: UserUpdate): User? {
        if (!CurrentUser.isInstructor || !CurrentUser.isAdmin) return null
        if (!repository.existsById(userId)) return null

        val isStudent = studentRepository.existsById(userId)
        val databaseUpdate = DatabaseUpdate(User::class.java, CurrentUser.id)

        return databaseUpdate
                .addUpdateQuery("language") {
                    updatedUser.language ?: it?.language
                }
                .addUpdateQuery("name") {
                    updatedUser.name ?: it?.name
                }
                .addConditionalUpdate("password", passwordEncoder.encode(updatedUser.password)) {
                    updatedUser.password != null
                }.addConditionalUpdate("school",
                        if (isStudent) updatedUser.school?.toList()?.get(0) as Any
                        else updatedUser.school
                ) {
                    updatedUser.school != null && (isStudent || instructorRepository.existsById(userId))
                }.apply()
    }
}