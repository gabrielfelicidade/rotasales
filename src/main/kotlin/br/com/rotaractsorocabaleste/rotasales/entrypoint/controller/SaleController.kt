package br.com.rotaractsorocabaleste.rotasales.entrypoint.controller

import br.com.rotaractsorocabaleste.rotasales.core.entity.Sale
import br.com.rotaractsorocabaleste.rotasales.core.service.SaleService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.Principal
import java.util.*

@RestController
@RequestMapping("/sales")
class SaleController(
    private val saleService: SaleService,
    private val logger: Logger = LoggerFactory.getLogger(SaleController::class.java)
) {

    @GetMapping
    fun getLoggedInUserSales(principal: Principal): ResponseEntity<List<Sale>> {
        return try {
            val ret = saleService.getLoggedInUserSales()

            logger.info("Getting all sales for seller=${principal.name}")

            ResponseEntity.ok(ret)
        } catch (e: Exception) {
            logger.error("Error while getting sales from seller=${principal.name}")

            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }

    @PostMapping
    fun create(@RequestBody sale: Sale): ResponseEntity<Sale> {
        logger.info("Received request for create a sale, sale for=${sale.customer}")

        return try {
            val ret = saleService.create(sale)

            logger.info("New sale saved, sale for=${ret.customer}")

            ResponseEntity.ok(ret)
        } catch (e: Exception) {
            logger.error("Error while inserting sale, sale for=${sale.customer}")

            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }

    @PutMapping
    fun update(@RequestBody sale: Sale): ResponseEntity<Sale> {
        logger.info("Received request for update a sale, sale for=${sale.customer}")

        return try {
            val ret = saleService.update(sale)

            logger.info("Sale updated, sale for=${ret?.customer}")

            ret?.let { ResponseEntity.ok(it) } as ResponseEntity<Sale>
        } catch (e: Exception) {
            logger.error("Error while updating sale, sale for=${sale.customer}")

            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }

    @DeleteMapping("/{saleId}")
    fun delete(@PathVariable saleId: UUID): ResponseEntity<Sale> {
        logger.info("Received request for delete a sale, id=${saleId}")

        return try {
            val ret = saleService.delete(saleId)

            logger.info("Sale deleted, id=${saleId}")

            ret.let { ResponseEntity.ok(it) } as ResponseEntity<Sale>
        } catch (e: Exception) {
            logger.error("Error while deleting sale, id=${saleId}")

            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }

}