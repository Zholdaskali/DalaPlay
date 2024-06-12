package com.example.dalaPlay.controller

import com.example.dalaPlay.model.request.LoginRequest
import com.example.dalaPlay.model.request.RegisterRequest
import com.example.dalaPlay.service.AuthenticationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin(origins = ["*"])
class AuthenticationController(
    private val authenticationService: AuthenticationService
) {

    @PostMapping("/register")
    fun register(@RequestBody registerRequest: RegisterRequest): ResponseEntity<Long?> {
        return try {
            val userId = authenticationService.register(
                registerRequest.userName,
                registerRequest.password,
                registerRequest.userPhone
            )
            ResponseEntity(userId, HttpStatus.CREATED)
        } catch (e: Exception) {
            ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<Long?> {
        return try {
            val userId = authenticationService.login(
                loginRequest.userName,
                loginRequest.password
            )
            if (userId != null) {
                ResponseEntity(userId, HttpStatus.OK)
            } else {
                ResponseEntity(null, HttpStatus.UNAUTHORIZED)
            }
        } catch (e: Exception) {
            ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}

////
//{
//    "userName": "John Doe",
//    "userPhone": "1234567890",
//    "password": "password1231"
//}
//{
//    "userName": "Zholdaskali Erkebulan",
//    "userPhone": "87478408845",
//    "password": "password1231"
//}