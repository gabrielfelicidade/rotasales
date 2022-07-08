package br.com.rotaractsorocabaleste.rotasales.institution;

import br.com.rotaractsorocabaleste.rotasales.common.entity.Auditable;
import br.com.rotaractsorocabaleste.rotasales.event.Event;
import br.com.rotaractsorocabaleste.rotasales.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Entity
@Getter
@NoArgsConstructor
@Setter
@With
public class Institution extends Auditable implements Serializable {

    private static final long serialVersionUID = 4284644376207283131L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    @NotNull
    private String name;
    @OneToMany(mappedBy = "institution", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Event> events;
    @OneToMany(mappedBy = "institution", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<User> users;

}
