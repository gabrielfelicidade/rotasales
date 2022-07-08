package br.com.rotaractsorocabaleste.rotasales.event;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping
    public Set<Event> findMyInstitutionActiveEvents() {
        return eventService.findMyInstitutionActiveEvents();
    }

    @PostMapping
    public Event create(@RequestBody @Valid final Event event) {
        return eventService.create(event);
    }

}
