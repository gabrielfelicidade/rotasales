package br.com.rotaractsorocabaleste.rotasales.core.service

import br.com.rotaractsorocabaleste.rotasales.core.entity.Sale
import java.util.*

interface SaleService {
    fun create(sale: Sale): Sale
    fun update(sale: Sale): Sale?
    fun delete(saleId: UUID): Boolean
    fun getLoggedInUserSales(): List<Sale>
}