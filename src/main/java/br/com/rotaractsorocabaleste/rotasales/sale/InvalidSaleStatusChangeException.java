package br.com.rotaractsorocabaleste.rotasales.sale;

import br.com.rotaractsorocabaleste.rotasales.common.exception.BusinessException;
import br.com.rotaractsorocabaleste.rotasales.common.exception.ErrorType;

public class InvalidSaleStatusChangeException extends BusinessException {

    public InvalidSaleStatusChangeException() {
        super(ErrorType.INVALID_SALE_STATUS_CHANGE);
    }

}
