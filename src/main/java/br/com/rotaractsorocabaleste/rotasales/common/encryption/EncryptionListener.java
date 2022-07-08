package br.com.rotaractsorocabaleste.rotasales.common.encryption;

import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.event.spi.LoadEvent;
import org.hibernate.event.spi.LoadEventListener;
import org.hibernate.event.spi.PostLoadEvent;
import org.hibernate.event.spi.PostLoadEventListener;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.hibernate.event.spi.PreLoadEvent;
import org.hibernate.event.spi.PreLoadEventListener;
import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EncryptionListener implements PreInsertEventListener, PreUpdateEventListener, PreLoadEventListener, PostLoadEventListener {

    private final FieldEncrypter fieldEncrypter;
    private final FieldDecrypter fieldDecrypter;

    @Override
    public boolean onPreInsert(final PreInsertEvent event) {
        Object[] state = event.getState();
        String[] propertyNames = event.getPersister().getPropertyNames();
        Object entity = event.getEntity();
        fieldEncrypter.encrypt(state, propertyNames, entity);
        return false;
    }

    @Override
    public void onPreLoad(final PreLoadEvent event) {
        fieldDecrypter.decrypt(event.getEntity());
    }

    @Override
    public boolean onPreUpdate(final PreUpdateEvent event) {
        Object[] state = event.getState();
        String[] propertyNames = event.getPersister().getPropertyNames();
        Object entity = event.getEntity();
        fieldEncrypter.encrypt(state, propertyNames, entity);
        return false;
    }

    @Override
    public void onPostLoad(final PostLoadEvent event) {
        fieldDecrypter.decrypt(event.getEntity());
    }
}
