package com.example.dalaPlay.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalTime

@Entity
@Table(name = "t_events")
data class Event(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @Column(name = "event_name", nullable = false)
    val eventName: String,

    @Column(name = "description")
    val eventDescription: String,

    @Column(name = "event_date", nullable = false)
    val eventDate: LocalDate,

    @Column(name = "event_time", nullable = false)
    val eventTime: LocalTime,

    @Column(name = "event_address", nullable = false)
    val eventAddress: String,

    @Column(name = "image_path", nullable = false)
    val imagePath: String?,

    @JsonIgnore
    @OneToMany(mappedBy = "event", cascade = [CascadeType.ALL], orphanRemoval = true)
    val registrations: MutableList<Registration> = mutableListOf()
)