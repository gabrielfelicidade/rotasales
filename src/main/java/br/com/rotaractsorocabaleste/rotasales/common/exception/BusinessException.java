package br.com.rotaractsorocabaleste.rotasales.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BusinessException extends RuntimeException {

    private final ErrorType errorType;

}
