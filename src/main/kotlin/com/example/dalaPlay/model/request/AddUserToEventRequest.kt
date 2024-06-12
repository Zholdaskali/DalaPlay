package com.example.dalaPlay.model.request

data class AddUserToEventRequest(
    val userId: Long,
    val eventId: Long
)