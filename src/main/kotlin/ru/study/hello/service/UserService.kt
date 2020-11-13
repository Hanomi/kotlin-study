package ru.study.hello.service

import ru.study.hello.model.User

interface UserService {

    fun findUserByEmail(email: String): User?

    fun saveUser(user: User): User
}