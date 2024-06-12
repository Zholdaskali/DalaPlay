package com.example.dalaPlay.service

import com.example.dalaPlay.encoder.BCryptPasswordEncoder
import com.example.dalaPlay.exception.UserNotFoundException
import com.example.dalaPlay.exception.UserAlreadyExistsException
import com.example.dalaPlay.exception.WrongCredentialsException
import com.example.dalaPlay.model.User
import com.example.dalaPlay.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class AuthenticationService(private val userRepository: UserRepository) {

    private val passwordEncoder = BCryptPasswordEncoder(10)

    fun register(userName: String, password: String, userPhone: String): Long? {
        if (userRepository.existsByUserName(userName)) {
            throw UserAlreadyExistsException()
        }

        val encodedPassword = passwordEncoder.hash(password)
        val customer = User(password = encodedPassword, userName = userName, userPhone = userPhone)
        userRepository.save(customer)

        return customer.id
    }

    fun login(userName: String, password: String): Long? {
        val customer = userRepository.findByUserName(userName)
            .orElseThrow { UserNotFoundException() }

        val passwordMatches = customer.password?.let {
            passwordEncoder.check(password, it)
        } ?: false

        if (!passwordMatches) {
            throw WrongCredentialsException()
        }

        return customer.id
    }
}