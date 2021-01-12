package br.com.rotaractsorocabaleste.rotasales.core.service.impl

import br.com.rotaractsorocabaleste.rotasales.core.entity.Event
import br.com.rotaractsorocabaleste.rotasales.core.service.EventService
import br.com.rotaractsorocabaleste.rotasales.dataprovider.repository.EventRepository
import org.springframework.stereotype.Service
import java.time.ZonedDateTime

@Service
class EventServiceImpl(
    private val eventRepository: EventRepository
) : EventService {

    override fun getActiveEvents(): List<Event> {
        return eventRepository
            .findByEndDateGreaterThanEqual(
                ZonedDateTime
                    .now()
                    .withHour(0)
                    .withMinute(0)
                    .withSecond(0)
            )
    }

}