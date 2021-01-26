package br.com.rotaractsorocabaleste.rotasales.core.entity

import java.time.ZonedDateTime
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "event")
data class Event(
    @Id
    @Column(name = "event_id")
    val id: UUID = UUID.randomUUID(),
    val description: String? = null,
    val startDate: ZonedDateTime = ZonedDateTime.now(),
    val endDate: ZonedDateTime = ZonedDateTime.now()
)