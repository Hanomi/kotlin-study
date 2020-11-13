package ru.study.hello.service

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import ru.study.hello.model.Role
import ru.study.hello.model.User
import ru.study.hello.repositories.RoleRepository
import ru.study.hello.repositories.UserRepository
import kotlin.collections.HashSet

@Service
class UserServiceImpl(
        var userRepository : UserRepository,
        var roleRepository : RoleRepository,
        var bCryptPasswordEncoder : BCryptPasswordEncoder
) : UserService {

    override fun findUserByEmail(email: String): User? =
        userRepository.findByEmail(email)

    override fun saveUser(user: User): User {
        user.password = bCryptPasswordEncoder.encode(user.password)
        user.active = 1
        val role = roleRepository.findByRole("ADMIN")
        user.roles = HashSet<Role>(listOf(role))
        return userRepository.save(user)
    }
}