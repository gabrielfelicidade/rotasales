package br.com.rotaractsorocabaleste.rotasales.common.encryption;

import lombok.RequiredArgsConstructor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;

@Component
@RequiredArgsConstructor
public class HibernateRegistry {

    private final EntityManagerFactory entityManagerFactory;
    private final EncryptionListener encryptionListener;

    @PostConstruct
    private void init() {
        final SessionFactoryImpl sessionFactory = entityManagerFactory.unwrap(SessionFactoryImpl.class);
        final EventListenerRegistry registry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);

        registry.appendListeners(EventType.PRE_LOAD, encryptionListener);
        registry.appendListeners(EventType.PRE_UPDATE, encryptionListener);
        registry.appendListeners(EventType.PRE_INSERT, encryptionListener);
        registry.appendListeners(EventType.POST_LOAD, encryptionListener);
    }
}