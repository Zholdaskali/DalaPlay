package com.example.dalaPlay.model

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "t_registrations")
data class Registration(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    val event: Event,

    @Column(name = "registration_date", nullable = false)
    val registrationDate: LocalDate = LocalDate.now()
)
