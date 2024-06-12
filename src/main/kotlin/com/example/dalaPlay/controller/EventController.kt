package com.example.dalaPlay.controller

import com.example.dalaPlay.model.Event
import com.example.dalaPlay.model.request.CreateEventRequest
import com.example.dalaPlay.service.EventService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/event")
@CrossOrigin(origins = ["*"])
class EventController(private val eventService: EventService) {

    @GetMapping("/")
    fun getAll(): ResponseEntity<List<Event>> {
        val events = eventService.getAllEvents()
        return if (events.isNotEmpty()) {
            ResponseEntity.ok(events)
        } else {
            ResponseEntity.noContent().build()
        }
    }

    @PostMapping("/add")
    fun create(@RequestBody request: CreateEventRequest): ResponseEntity<Event> {
        return try {
            val event = eventService.createEvent(
                userId = request.userId,
                eventDescription = request.eventDescription,
                eventDate = request.eventDate,
                eventAddress = request.eventAddress,
                imagePath = request.imagePath
            )
            ResponseEntity.status(HttpStatus.CREATED).body(event)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        }
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        return try {
            eventService.deleteEventById(id)
            ResponseEntity.noContent().build()
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }

    @GetMapping("/user/{userId}")
    fun getByUserId(@PathVariable userId: Long): ResponseEntity<List<Event>> {
        val events = eventService.getEventsByUserId(userId)
        return if (events.isNotEmpty()) {
            ResponseEntity.ok(events)
        } else {
            ResponseEntity.noContent().build()
        }
    }
}
