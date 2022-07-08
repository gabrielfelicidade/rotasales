package br.com.rotaractsorocabaleste.rotasales.common.validation;

import br.com.rotaractsorocabaleste.rotasales.common.exception.BusinessException;

public interface Validator<T> {

    void validate(final T t) throws BusinessException;

}
