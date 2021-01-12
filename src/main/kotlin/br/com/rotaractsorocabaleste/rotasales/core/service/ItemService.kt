package br.com.rotaractsorocabaleste.rotasales.core.service

import br.com.rotaractsorocabaleste.rotasales.core.entity.Item

interface ItemService {
    fun save(item: Item): Item
    fun getAll(): List<Item>
}