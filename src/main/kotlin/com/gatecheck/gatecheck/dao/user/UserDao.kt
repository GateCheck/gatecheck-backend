package com.gatecheck.gatecheck.dao.user

import com.gatecheck.gatecheck.model.entity.User
import java.util.*

interface UserDao {
    /**
     * Get the currently logged in user.
     * @return Currently logged in user. If no user is logged in Optional is empty.
     */
    fun getUser(): Optional<User>

    /**
     * Gets many users simultaneously.
     * @param user UUID's to all users to get. filters out those the currently logged in user doesn't have access to
     * @param allUsers If true, ignores `user` parameter and gets all users the currently logged in user has access to
     * @return Set of all users the currently logged in user is allowed to access with applied filters.
     */
    fun getUser(vararg user: UUID, allUsers: Boolean): Set<User>

    /**
     * Sends email the currently logged in user with link that will delete account
     * @return User that might be deleted
     */
    fun deleteUser(): User

    /**
     * Update any field of currently logged in user besides adding instructors or parents.
     * Instructors can add students to themselves since they are trusted. Can't update UUID.
     * @return updated user
     */
    fun updateUser(user: User): User

    /**
     * Update any field besides contact information. Only for Instructor of user passed, Can't update UUID.
     * @return updated user.
     */
    fun updateUser(userId: UUID, user: User): User
}