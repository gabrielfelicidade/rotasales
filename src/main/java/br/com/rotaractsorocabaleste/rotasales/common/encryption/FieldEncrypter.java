package br.com.rotaractsorocabaleste.rotasales.common.encryption;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

@Component
@RequiredArgsConstructor
public class FieldEncrypter {

    public void encrypt(final Object[] state, final String[] propertyNames, final Object entity) {
        ReflectionUtils.doWithFields(entity.getClass(), field -> encryptField(field, state, propertyNames), EncryptionUtils::isFieldEncrypted);
    }

    private void encryptField(final Field field, final Object[] state, final String[] propertyNames) {
        final int propertyIndex = EncryptionUtils.getPropertyIndex(field.getName(), propertyNames);
        final Object currentValue = state[propertyIndex];

        if (!(currentValue instanceof String)) {
            throw new IllegalStateException("Encrypted annotation was used on a non-String field");
        }

        state[propertyIndex] = EncryptionUtils.encrypt(currentValue.toString());
    }
}
