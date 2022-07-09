package br.com.rotaractsorocabaleste.rotasales.item;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {

    Optional<Item> findByIdAndEventId(final UUID id, final UUID eventId);

    Set<Item> findByEventIdOrderByNameAsc(final UUID eventId);

}
