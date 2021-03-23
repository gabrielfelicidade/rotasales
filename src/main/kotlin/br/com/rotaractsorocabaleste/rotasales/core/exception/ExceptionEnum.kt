package br.com.rotaractsorocabaleste.rotasales.core.exception

enum class ExceptionEnum(
    val reason: String,
    val message: String
) {
    BAD_REQUEST_SALE_ALREADY_DELIVERED("O pedido já foi entregue!", "The sale for id=%s has already been delivered!"),
    BAD_REQUEST_CANNOT_RETURN_STATUS( "O status atual do pedido é posterior ao da alteração solicitada!", "New status should be greater than actual status for sale id=%s."),
    NOT_FOUND_ENTITY_NOT_FOUND("O recurso especificado não pôde ser encontrado.", "Entity for id=%s not found."),
    FORBIDDEN_USER_UNAUTHORIZED("Você não possui permissão para realizar a operação.", "User requested an action be taken on a resource that doesn't have permission. UserId=%s")
}