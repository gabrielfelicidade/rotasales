package br.com.rotaractsorocabaleste.rotasales.core.service

import br.com.rotaractsorocabaleste.rotasales.core.entity.SaleItem
import br.com.rotaractsorocabaleste.rotasales.core.vo.SaleRequestVO
import java.util.*

interface SaleItemService {
    fun saveSaleItems(saleItems: List<SaleItem>): List<SaleItem>
    fun getSaleItemsBySaleId(saleId: UUID): List<SaleItem>
    fun removeSaleItems(saleItems: List<SaleItem>)
}