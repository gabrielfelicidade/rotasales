package br.com.rotaractsorocabaleste.rotasales.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public Item create(final Item item) {
        return itemRepository.save(item);
    }

    @Override
    public boolean belongsToEvent(final UUID id,
                                  final UUID eventId) {
        return itemRepository.findByIdAndEventId(id, eventId).isPresent();
    }

    @Override
    public Set<Item> findItemsByEventId(final UUID eventId) {
        return itemRepository.findByEventId(eventId);
    }

}
