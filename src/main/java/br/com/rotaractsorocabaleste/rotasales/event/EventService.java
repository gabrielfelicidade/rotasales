package br.com.rotaractsorocabaleste.rotasales.event;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface EventService {

    Event create(final Event event);

    Optional<Event> findById(final UUID id);

    boolean existsById(final UUID id);

    Set<Event> findMyInstitutionActiveEvents();
}
