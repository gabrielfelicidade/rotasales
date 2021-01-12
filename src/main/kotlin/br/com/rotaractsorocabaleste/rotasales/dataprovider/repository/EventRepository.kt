package br.com.rotaractsorocabaleste.rotasales.dataprovider.repository

import br.com.rotaractsorocabaleste.rotasales.core.entity.Event
import org.springframework.data.repository.CrudRepository
import java.time.ZonedDateTime
import java.util.*

interface EventRepository : CrudRepository<Event, UUID> {
    fun findByEndDateGreaterThanEqual(endDate: ZonedDateTime): List<Event>
}