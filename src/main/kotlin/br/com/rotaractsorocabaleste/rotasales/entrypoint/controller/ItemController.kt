package br.com.rotaractsorocabaleste.rotasales.entrypoint.controller

import br.com.rotaractsorocabaleste.rotasales.core.entity.Item
import br.com.rotaractsorocabaleste.rotasales.core.service.ItemService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/items")
class ItemController(
        private val itemService: ItemService,
        private val logger: Logger = LoggerFactory.getLogger(ItemController::class.java)
) {

    @GetMapping
    fun getAll(): ResponseEntity<List<Item>> {
        return try {
            val ret = itemService.getAll()

            logger.info("Getting all items, number of items=${ret.size}")

            ResponseEntity.ok(ret)
        } catch(e: Exception) {
            logger.error("Error while getting all items")

            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }

    @PostMapping
    fun create(@RequestBody item: Item): ResponseEntity<Item> {
        logger.info("Received request for create an item, request=$item")

        return try{
            val ret = itemService.save(item)

            logger.info("New item saved, item=$ret")

            ResponseEntity.ok(ret)
        }catch(e: Exception){
            logger.error("Error while saving item, item=$item")

            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }

}