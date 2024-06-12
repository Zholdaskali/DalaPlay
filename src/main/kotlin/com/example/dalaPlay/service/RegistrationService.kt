package com.example.dalaPlay.service

import com.example.dalaPlay.model.Event
import com.example.dalaPlay.model.Registration
import com.example.dalaPlay.model.User
import com.example.dalaPlay.repository.EventRepository
import com.example.dalaPlay.repository.RegistrationRepository
import com.example.dalaPlay.repository.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class RegistrationService(
    private val registrationRepository: RegistrationRepository,
    private val userRepository: UserRepository,
    private val eventRepository: EventRepository
    ) {

    fun addUserToEvent(userId: Long, eventId: Long) {
        val user = userRepository.findById(userId)
        val event = eventRepository.findById(eventId)

        if (user.isPresent && event.isPresent) {
            val registration = Registration(user = user.get(), event = event.get())
            registrationRepository.save(registration)
        }
    }

    fun getUsersByEventId(eventId: Long): List<User> {
        val registrations = registrationRepository.findByEventId(eventId)
        return registrations.map { it.user }
    }

    fun getEventsByUserId(userId: Long): List<Event> {
        val registrations = registrationRepository.findByUserId(userId)
        return registrations.map { it.event }
    }

    fun deleteRegistrationById(registrationId: Long) {
        registrationRepository.deleteById(registrationId)
    }
}