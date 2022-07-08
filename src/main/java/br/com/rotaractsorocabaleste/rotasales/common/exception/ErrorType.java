package br.com.rotaractsorocabaleste.rotasales.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Getter
@RequiredArgsConstructor
public enum ErrorType {

    INVALID_SALE("INVALID_SALE","A venda especificada é inválida."),
    SALE_NOT_FOUND("SALE_NOT_FOUND", "A venda informada não existe."),
    SALE_NOT_BELONGS_TO_LOGGED_IN_USER("SALE_NOT_BELONGS_TO_LOGGED_IN_USER", "A venda informada não pertence ao usuário logado."),
    INVALID_SALE_STATUS_CHANGE("INVALID_SALE_STATUS_CHANGE", "Não é possível retornar o status da venda.");

    private String errorCode;
    private String message;

}
