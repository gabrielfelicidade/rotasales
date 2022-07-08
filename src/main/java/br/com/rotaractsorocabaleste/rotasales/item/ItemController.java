package br.com.rotaractsorocabaleste.rotasales.item;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public Set<Item> findItemsByEventId(@RequestParam final UUID eventId) {
        return itemService.findItemsByEventId(eventId);
    }

    @PostMapping
    public Item create(@RequestBody @Valid final Item item) {
        return itemService.create(item);
    }

}
