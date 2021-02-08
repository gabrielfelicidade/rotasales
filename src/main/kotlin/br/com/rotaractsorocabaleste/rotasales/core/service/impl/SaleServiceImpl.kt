package br.com.rotaractsorocabaleste.rotasales.core.service.impl

import br.com.rotaractsorocabaleste.rotasales.core.entity.Sale
import br.com.rotaractsorocabaleste.rotasales.core.entity.SaleItem
import br.com.rotaractsorocabaleste.rotasales.core.service.SaleItemService
import br.com.rotaractsorocabaleste.rotasales.core.service.SaleService
import br.com.rotaractsorocabaleste.rotasales.core.service.UserService
import br.com.rotaractsorocabaleste.rotasales.dataprovider.repository.SaleRepository
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class SaleServiceImpl(
    private val saleRepository: SaleRepository,
    private val saleItemService: SaleItemService,
    private val userService: UserService
) : SaleService {
    override fun create(sale: Sale): Sale {
        val savedSale = saleRepository.save(
            sale.copy(
                seller = userService.getLoggedInUser(),
                items = listOf()
            )
        )
        val savedSaleItems = saleItemService.saveSaleItems(
            sale.items.map {
                it.copy(
                    sale = Sale(
                        id = sale.id
                    )
                )
            }
        )

        return savedSale.copy(items = savedSaleItems)
    }

    override fun update(sale: Sale): Sale? =
        if (checkSaleAlreadyExists(sale.id)) {
            val oldSaleItems = saleItemService.getSaleItemsBySaleId(sale.id)
            val newSaleItems = getSaleItemsForSale(sale)

            saleItemService.removeSaleItems(getPendingRemoveSaleItems(oldSaleItems, newSaleItems))

            val savedItems = saleItemService.saveSaleItems(newSaleItems)

            sale.copy(
                items = savedItems
            )
        } else {
            throw Exception("Bad Request")
        }

    override fun delete(saleId: UUID): Boolean {
        val sale = saleRepository.findById(saleId).get()

        if (checkSaleBelongsToLoggedInUser(sale)) {
            saleRepository.save(
                sale.copy(
                    active = false
                )
            )

            return true
        }

        return false
    }

    override fun getLoggedInUserSales(): List<Sale> =
        saleRepository.findBySellerIdAndActiveTrue(sellerId = userService.getLoggedInUser().id)

    private fun getSaleItemsForSale(sale: Sale): List<SaleItem> =
        sale.items
            .parallelStream()
            .map {
                SaleItem(
                    id = it.id,
                    sale = Sale(sale.id),
                    item = it.item,
                    amount = it.amount,
                    unitaryValue = it.unitaryValue
                )
            }.collect(Collectors.toList())

    private fun getPendingRemoveSaleItems(oldSaleItems: List<SaleItem>, newSaleItems: List<SaleItem>): List<SaleItem> {
        val newSaleItemsIds = newSaleItems.map { it.id }

        return oldSaleItems
            .parallelStream()
            .filter { it.id !in newSaleItemsIds }
            .collect(Collectors.toList())
    }

    private fun checkSaleAlreadyExists(saleId: UUID): Boolean = saleRepository.existsById(saleId)

    private fun checkSaleBelongsToLoggedInUser(sale: Sale): Boolean =
        sale.seller!!.id.toString() == userService.getLoggedInUser().id.toString()
}