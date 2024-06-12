package com.example.dalaPlay.model.request

import java.time.LocalDate

data class CreateEventRequest(
    val userId: Long,
    val eventDescription: String,
    val eventDate: LocalDate,
    val eventAddress: String,
    val imagePath: String
)

