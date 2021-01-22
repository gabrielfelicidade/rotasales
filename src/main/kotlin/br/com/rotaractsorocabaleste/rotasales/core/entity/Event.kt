package br.com.rotaractsorocabaleste.rotasales.core.entity

import java.time.ZonedDateTime
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Event(
    @Id
    val id: UUID = UUID.randomUUID(),
    @Column(nullable = false)
    val description: String? = null,
    @Column(nullable = false)
    val startDate: ZonedDateTime = ZonedDateTime.now(),
    @Column(nullable = false)
    val endDate: ZonedDateTime = ZonedDateTime.now()
)