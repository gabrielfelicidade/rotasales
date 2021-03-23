package br.com.rotaractsorocabaleste.rotasales.core.exception

class EntityNotFoundException(
    entityId: String
) : GenericException(
    ExceptionEnum.NOT_FOUND_ENTITY_NOT_FOUND.reason,
    ExceptionEnum.NOT_FOUND_ENTITY_NOT_FOUND.message.format(entityId)
)