package com.example.dalaPlay.controller

import com.example.dalaPlay.model.Event
import com.example.dalaPlay.model.User
import com.example.dalaPlay.model.request.AddUserToEventRequest
import com.example.dalaPlay.service.RegistrationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/registrations")
@CrossOrigin(origins = ["*"])
class RegistrationController(
    private val registrationService: RegistrationService
) {
    @PostMapping("/add")
    fun addUserToEvent(@RequestBody request: AddUserToEventRequest): ResponseEntity<Void> {
        return try {
            registrationService.addUserToEvent(request.userId, request.eventId)
            ResponseEntity.ok().build()
        } catch (e: IllegalArgumentException) {
            ResponseEntity.status(HttpStatus.CONFLICT).body(null)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null)
        }
    }

    @GetMapping("/events/{eventId}/users")
    fun getUsersByEventId(
        @PathVariable eventId: Long
    ): ResponseEntity<List<User>> {
        val users = registrationService.getUsersByEventId(eventId)
        return if (users.isNotEmpty()) {
            ResponseEntity.ok(users)
        } else {
            ResponseEntity.noContent().build()
        }
    }

    @GetMapping("/users/{userId}/events")
    fun getEventsByUserId(
        @PathVariable userId: Long
    ): ResponseEntity<List<Event>> {
        val events = registrationService.getEventsByUserId(userId)
        return if (events.isNotEmpty()) {
            ResponseEntity.ok(events)
        } else {
            ResponseEntity.noContent().build()
        }
    }

    @DeleteMapping("/delete/{registrationId}")
    fun deleteRegistrationById(
        @PathVariable registrationId: Long
    ): ResponseEntity<Void> {
        return try {
            registrationService.deleteRegistrationById(registrationId)
            ResponseEntity.noContent().build()
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }
}
