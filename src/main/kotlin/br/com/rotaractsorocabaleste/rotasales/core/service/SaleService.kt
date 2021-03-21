package br.com.rotaractsorocabaleste.rotasales.core.service

import br.com.rotaractsorocabaleste.rotasales.core.entity.Sale
import br.com.rotaractsorocabaleste.rotasales.core.vo.PatchSaleStatusRequestVO
import java.util.*

interface SaleService {
    fun create(sale: Sale)
    fun update(sale: Sale)
    fun delete(saleId: UUID)
    fun getLoggedInUserSales(): List<Sale>
    fun patchSaleStatus(patchSaleStatusRequestVO: PatchSaleStatusRequestVO)
    fun findById(saleId: UUID): Optional<Sale>
}