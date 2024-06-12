package com.example.dalaPlay.repository

import com.example.dalaPlay.model.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, Long> {
    fun existsByUserName(login: String): Boolean
    fun findByUserName(login: String): Optional<User>
}