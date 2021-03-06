package br.com.rotaractsorocabaleste.rotasales.entrypoint.controller

import br.com.rotaractsorocabaleste.rotasales.core.entity.Event
import br.com.rotaractsorocabaleste.rotasales.core.service.EventService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/events")
class EventController(
    private val eventService: EventService,
    private val logger: Logger = LoggerFactory.getLogger(EventController::class.java)
) {

    @GetMapping
    fun getActiveEvents(): ResponseEntity<List<Event>> {
        val ret = eventService.getActiveEvents()

        logger.info("Getting all active events, number of events=${ret.size}")

        return ResponseEntity.ok(ret)
    }

}