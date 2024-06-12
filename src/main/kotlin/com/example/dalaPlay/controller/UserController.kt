package com.example.dalaPlay.controller

import com.example.dalaPlay.exception.UserNotFoundException
import com.example.dalaPlay.model.User
import com.example.dalaPlay.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = ["*"])
class UserController(
    private val userService: UserService
) {
    @GetMapping("/{userId}")
    fun getUser(@PathVariable userId: Long): ResponseEntity<User> {
        return try {
            val user = userService.getById(userId)
            ResponseEntity(user, HttpStatus.OK)
        } catch (e: UserNotFoundException) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}
