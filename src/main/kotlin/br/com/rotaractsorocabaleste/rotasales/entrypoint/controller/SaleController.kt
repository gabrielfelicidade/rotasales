package br.com.rotaractsorocabaleste.rotasales.entrypoint.controller

import br.com.rotaractsorocabaleste.rotasales.core.entity.Sale
import br.com.rotaractsorocabaleste.rotasales.core.service.SaleService
import br.com.rotaractsorocabaleste.rotasales.core.vo.SaleRequestVO
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/sales")
class SaleController(
    private val saleService: SaleService,
    private val logger: Logger = LoggerFactory.getLogger(SaleController::class.java)
) {

    @GetMapping("/{sellerId}")
    fun getSalesBySellerId(@PathVariable sellerId: UUID): ResponseEntity<List<SaleRequestVO>> {
        return try {
            val ret = saleService.getSalesBySellerId(sellerId)

            logger.info("Getting all sales for sellerId=$sellerId, number of sales=${ret.size}")

            ResponseEntity.ok(ret)
        } catch (e: Exception) {
            logger.error("Error while getting sales from sellerId=$sellerId")

            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }

    @PostMapping
    fun create(@RequestBody saleRequestVO: SaleRequestVO): ResponseEntity<Sale> {
        logger.info("Received request for create a sale, request=$saleRequestVO")

        return try {
            val ret = saleService.create(saleRequestVO)

            logger.info("New sale saved, sale={}", ret)

            ResponseEntity.ok(ret)
        } catch (e: Exception) {
            logger.error("Error while inserting sale, sale=$saleRequestVO")

            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }

    @PutMapping
    fun update(@RequestBody saleRequestVO: SaleRequestVO): ResponseEntity<Sale> {
        logger.info("Received request for update a sale, request=$saleRequestVO")

        return try {
            val ret = saleService.update(saleRequestVO)

            logger.info("Sale updated, sale=$ret")

            ret?.let { ResponseEntity.ok(it) } as ResponseEntity<Sale>
        } catch (e: Exception) {
            logger.error("Error while updating sale, sale=$saleRequestVO")

            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }

    @DeleteMapping("/{saleId}")
    fun delete(@PathVariable saleId: UUID): ResponseEntity<Sale> {
        logger.info("Received request for delete a sale, id=$saleId")

        return try {
            val ret = saleService.delete(saleId)

            logger.info("Sale deleted, id=$saleId")

            ret.let { ResponseEntity.ok(it) } as ResponseEntity<Sale>
        } catch (e: Exception) {
            logger.error("Error while deleting sale, id=$saleId")

            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }

}