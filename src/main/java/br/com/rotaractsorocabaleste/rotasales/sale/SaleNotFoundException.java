package br.com.rotaractsorocabaleste.rotasales.sale;

import br.com.rotaractsorocabaleste.rotasales.common.exception.BusinessException;
import br.com.rotaractsorocabaleste.rotasales.common.exception.ErrorType;

public class SaleNotFoundException extends BusinessException {

    public SaleNotFoundException() {
        super(ErrorType.SALE_NOT_FOUND);
    }

}
