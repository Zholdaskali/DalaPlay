package com.example.dalaPlay.service

import com.example.dalaPlay.model.Event
import com.example.dalaPlay.repository.EventRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalTime

@Service
class EventService(
    private val eventRepository: EventRepository,
    private val userService: UserService
) {
    fun getAllEvents(): List<Event> = eventRepository.findAll()

    fun createEvent(userId: Long, eventName: String, eventDescription: String, eventDate: LocalDate, eventTime: LocalTime, eventAddress: String, imagePath: String): Event {
        val user = userService.getById(userId)
        val event = Event(user = user, eventName = eventName, eventDescription = eventDescription, eventDate = eventDate, eventTime = eventTime, eventAddress = eventAddress, imagePath = imagePath)
        return eventRepository.save(event)
    }

    fun deleteEventById(id: Long) {
        eventRepository.deleteById(id)
    }

    fun getEventsByUserId(userId: Long): List<Event> = eventRepository.findByUserId(userId)
}
