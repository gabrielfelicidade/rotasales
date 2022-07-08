package br.com.rotaractsorocabaleste.rotasales.common.encryption;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class StringEncryptionConverter implements
        AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(final String attribute) {
        return EncryptionUtils.encrypt(attribute);
    }

    @Override
    public String convertToEntityAttribute(final String dbData) {
        return EncryptionUtils.decrypt(dbData);
    }

}
