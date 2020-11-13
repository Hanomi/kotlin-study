package ru.study.hello.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import ru.study.hello.model.Role
import ru.study.hello.model.User

@Repository
interface UserRepository : JpaRepository<User, Int> {
    fun findByEmail(email: String): User?
}

@Repository
interface RoleRepository : JpaRepository<Role, Int> {
    fun findByRole(role: String): Role?
}

