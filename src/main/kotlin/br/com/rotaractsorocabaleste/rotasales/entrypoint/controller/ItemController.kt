package br.com.rotaractsorocabaleste.rotasales.entrypoint.controller

import br.com.rotaractsorocabaleste.rotasales.core.entity.Item
import br.com.rotaractsorocabaleste.rotasales.core.service.ItemService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.*

@RestController
@RequestMapping("/items")
class ItemController(
    private val itemService: ItemService,
    private val logger: Logger = LoggerFactory.getLogger(ItemController::class.java)
) {

    @GetMapping
    fun getAllByEvent(
        @RequestParam("event") eventId: UUID
    ): ResponseEntity<List<Item>> {
        val ret = itemService.findByEventId(eventId)

        logger.info("Getting items for eventId=${eventId}")

        return ResponseEntity.ok(ret)
    }

    @PostMapping
    fun create(@RequestBody item: Item): ResponseEntity<Item> {
        logger.info("Received request for create an item, item description=${item.description}")

        val ret = itemService.save(item)

        logger.info("New item saved, item description=${ret.description}")

        return ResponseEntity.created(URI("/items")).build()
    }

}