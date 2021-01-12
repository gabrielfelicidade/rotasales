package br.com.rotaractsorocabaleste.rotasales.core.service

import br.com.rotaractsorocabaleste.rotasales.core.entity.Event

interface EventService {
    fun getActiveEvents(): List<Event>
}