package br.com.rotaractsorocabaleste.rotasales.common.encryption;

import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EncryptionUtils {

    public static String encrypt(final String value) {
        return Base64.getEncoder().encodeToString(value.getBytes(StandardCharsets.UTF_8));
    }

    public static String decrypt(final String value) {
        return new String(Base64.getDecoder().decode(value.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
    }

    public static boolean isFieldEncoded(final String value) {
        try {
            Base64.getDecoder().decode(value);
            return true;
        } catch(IllegalArgumentException e) {
            return false;
        }
    }

    public static boolean isFieldEncrypted(final Field field) {
        return AnnotationUtils.findAnnotation(field, Encrypted.class) != null;
    }

    public static int getPropertyIndex(final String name, final String[] properties) {
        for (int i = 0; i < properties.length; i++) {
            if (name.equals(properties[i])) {
                return i;
            }
        }

        throw new IllegalArgumentException("No property was found for name " + name);
    }

}
