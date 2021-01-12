package br.com.rotaractsorocabaleste.rotasales.core.entity

import java.time.ZonedDateTime
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Event(
        @Id
        val id: UUID = UUID.randomUUID(),
        val description: String? = null,
        val startDate: ZonedDateTime = ZonedDateTime.now(),
        val endDate: ZonedDateTime = ZonedDateTime.now()
)