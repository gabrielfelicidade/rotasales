package br.com.rotaractsorocabaleste.rotasales.common.exception;

import lombok.Getter;

@Getter
public class ErrorDTO {

    private final String errorCode;
    private final String message;

    public ErrorDTO(final ErrorType errorType) {
        this.errorCode = errorType.getErrorCode();
        this.message = errorType.getMessage();
    }

}
