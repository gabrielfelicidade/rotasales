package br.com.rotaractsorocabaleste.rotasales.core.service

import br.com.rotaractsorocabaleste.rotasales.core.entity.Item
import java.util.*

interface ItemService {
    fun save(item: Item): Item
    fun findByEventId(eventId: UUID): List<Item>
}