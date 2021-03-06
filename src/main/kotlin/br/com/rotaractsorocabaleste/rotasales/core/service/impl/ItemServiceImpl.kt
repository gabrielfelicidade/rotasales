package br.com.rotaractsorocabaleste.rotasales.core.service.impl

import br.com.rotaractsorocabaleste.rotasales.core.entity.Item
import br.com.rotaractsorocabaleste.rotasales.core.service.ItemService
import br.com.rotaractsorocabaleste.rotasales.dataprovider.repository.ItemRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ItemServiceImpl(
    private val itemRepository: ItemRepository
) : ItemService {
    override fun save(item: Item): Item = itemRepository.save(item)

    override fun findByEventId(eventId: UUID): List<Item> = itemRepository.findByEventId(eventId)
}