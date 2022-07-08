package br.com.rotaractsorocabaleste.rotasales.event;

import br.com.rotaractsorocabaleste.rotasales.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final UserService userService;

    @Override
    public Event create(final Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Optional<Event> findById(final UUID id) {
        return eventRepository.findById(id);
    }

    @Override
    public boolean existsById(final UUID id) {
        return eventRepository.existsById(id);
    }

    @Override
    public Set<Event> findMyInstitutionActiveEvents() {
        final var loggedInUser = userService.findLoggedInUser();
        final var institutionId = loggedInUser.getInstitution().getId();

        return eventRepository.findByInstitutionIdAndActive(institutionId);
    }

}
