package br.com.rotaractsorocabaleste.rotasales.sale;

import br.com.rotaractsorocabaleste.rotasales.common.exception.BusinessException;
import br.com.rotaractsorocabaleste.rotasales.common.exception.ErrorType;

public class SaleNotBelongsToLoggedInUserException extends BusinessException {

    public SaleNotBelongsToLoggedInUserException() {
        super(ErrorType.SALE_NOT_BELONGS_TO_LOGGED_IN_USER);
    }
}
