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

    fun createEvent(
        userId: Long,
        eventDescription: String,
        eventDate: LocalDate,
        eventTime: LocalTime, // Добавлено время
        eventAddress: String,
        imagePath: String?
    ): Event {
        val user = userService.getById(userId) // Получаем пользователя по его ID
        val event = Event(
            user = user,
            eventDescription = eventDescription,
            eventDate = eventDate,
            eventTime = eventTime, // Добавлено время
            eventAddress = eventAddress,
            imagePath = imagePath
        )
        return eventRepository.save(event) // Сохраняем событие в репозитории
    }

    fun deleteEventById(id: Long) {
        eventRepository.deleteById(id)
    }

    fun getEventsByUserId(userId: Long): List<Event> = eventRepository.findByUserId(userId)
}
