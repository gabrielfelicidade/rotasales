package br.com.rotaractsorocabaleste.rotasales.item;

import java.util.Set;
import java.util.UUID;

public interface ItemService {

    Item create(final Item item);

    boolean belongsToEvent(final UUID id, final UUID eventId);

    Set<Item> findItemsByEventId(final UUID eventId);
}
