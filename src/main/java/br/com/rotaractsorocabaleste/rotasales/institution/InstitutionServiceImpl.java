package br.com.rotaractsorocabaleste.rotasales.institution;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InstitutionServiceImpl implements InstitutionService {

    private final InstitutionRepository institutionRepository;

    @Override
    public Institution create(final Institution institution) {
        return institutionRepository.save(institution);
    }

}
