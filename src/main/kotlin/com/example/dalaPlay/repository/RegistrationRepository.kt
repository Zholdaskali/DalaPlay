package com.example.dalaPlay.repository

import com.example.dalaPlay.model.Registration
import org.springframework.data.jpa.repository.JpaRepository

interface RegistrationRepository : JpaRepository<Registration, Long> {
    fun findByEventId(eventId: Long): List<Registration>
    fun findByUserId(userId: Long): List<Registration>

}