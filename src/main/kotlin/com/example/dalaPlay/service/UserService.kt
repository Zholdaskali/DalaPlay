package com.example.dalaPlay.service

import com.example.dalaPlay.model.User
import com.example.dalaPlay.repository.UserRepository
import com.example.dalaPlay.exception.UserNotFoundException
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun getById(userId: Long): User {
        return userRepository.findById(userId).orElseThrow { UserNotFoundException() }
    }
}