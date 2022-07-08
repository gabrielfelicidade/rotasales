package br.com.rotaractsorocabaleste.rotasales.common.encryption;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Base64;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class FieldDecrypter {

    public void decrypt(final Object entity) {
        ReflectionUtils.doWithFields(entity.getClass(), field -> decryptField(field, entity), EncryptionUtils::isFieldEncrypted);
    }

    private void decryptField(final Field field, final Object entity) {
        field.setAccessible(true);
        Object value = ReflectionUtils.getField(field, entity);

        if (Objects.isNull(value)) {
            return;
        }

        if (!(value instanceof String)) {
            throw new IllegalStateException("Encrypted annotation was used on a non-String field");
        }

        if (!EncryptionUtils.isFieldEncoded(value.toString())) {
            return;
        }

        ReflectionUtils.setField(field, entity, EncryptionUtils.decrypt(value.toString()));
    }
}
