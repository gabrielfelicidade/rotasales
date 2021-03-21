package br.com.rotaractsorocabaleste.rotasales.core.exception

class EntityNotFoundException(
    entityId: String
) : Exception("Entity for id=$entityId not found.")