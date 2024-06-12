package com.example.dalaPlay.repository

import com.example.dalaPlay.model.Event
import org.springframework.data.jpa.repository.JpaRepository

interface EventRepository : JpaRepository<Event, Long> {
    fun findByUserId(userId: Long): List<Event>
}

