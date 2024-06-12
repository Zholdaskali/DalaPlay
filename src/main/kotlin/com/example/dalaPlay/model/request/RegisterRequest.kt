package com.example.dalaPlay.model.request

data class RegisterRequest(
    val password: String,
    val userName: String,
    val userPhone: String
)