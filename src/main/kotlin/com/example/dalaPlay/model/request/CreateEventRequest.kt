package com.example.dalaPlay.model.request

import java.time.LocalDate
import java.time.LocalTime

data class CreateEventRequest(
    val userId: Long,
    val eventDescription: String,
    val eventDate: LocalDate,
    val eventTime: LocalTime,
    val eventAddress: String,
    val imagePath: String
)

