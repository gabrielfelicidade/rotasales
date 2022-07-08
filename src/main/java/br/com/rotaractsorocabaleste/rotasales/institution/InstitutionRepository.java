package br.com.rotaractsorocabaleste.rotasales.institution;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InstitutionRepository extends JpaRepository<Institution, UUID> {
}
