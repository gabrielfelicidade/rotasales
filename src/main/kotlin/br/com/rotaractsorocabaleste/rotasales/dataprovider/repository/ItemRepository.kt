package br.com.rotaractsorocabaleste.rotasales.dataprovider.repository

import br.com.rotaractsorocabaleste.rotasales.core.entity.Item
import org.springframework.data.repository.CrudRepository
import java.util.*

interface ItemRepository : CrudRepository<Item, UUID> {
    fun findByEventId(eventId: UUID): List<Item>
}