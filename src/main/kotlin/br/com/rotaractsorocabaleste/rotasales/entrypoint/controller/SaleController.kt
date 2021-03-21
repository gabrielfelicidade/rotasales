package br.com.rotaractsorocabaleste.rotasales.entrypoint.controller

import br.com.rotaractsorocabaleste.rotasales.core.entity.Sale
import br.com.rotaractsorocabaleste.rotasales.core.service.SaleService
import br.com.rotaractsorocabaleste.rotasales.core.vo.PatchSaleStatusRequestVO
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
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
        val ret = saleService.getLoggedInUserSales()

        logger.info("Getting all sales for seller=${principal.name}")

        return ResponseEntity.ok(ret)
    }

    @PostMapping
    fun create(@RequestBody sale: Sale): ResponseEntity<Any> {
        logger.info("Received request for create a sale, sale for=${sale.customer}")

        saleService.create(sale)

        logger.info("New sale saved, sale for=${sale.customer}")

        return ResponseEntity.created(URI.create("/sales")).build()
    }

    @PutMapping
    fun update(@RequestBody sale: Sale): ResponseEntity<Any> {
        logger.info("Received request for update a sale, sale for=${sale.customer}")

        saleService.update(sale)

        logger.info("Sale updated, sale for=${sale.customer}")

        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{saleId}")
    fun delete(@PathVariable saleId: UUID): ResponseEntity<Any> {
        saleService.delete(saleId)

        logger.info("Sale deleted, saleId=${saleId}")

        return ResponseEntity.ok().build()
    }

    @PatchMapping
    fun patchSaleStatus(@RequestBody patchSaleStatusRequestVO: PatchSaleStatusRequestVO): ResponseEntity<Any> {
        logger.info("Received request for change saleId=${patchSaleStatusRequestVO.saleId}} to status=${patchSaleStatusRequestVO.status}")

        saleService.patchSaleStatus(patchSaleStatusRequestVO)

        return ResponseEntity.ok().build()
    }

}