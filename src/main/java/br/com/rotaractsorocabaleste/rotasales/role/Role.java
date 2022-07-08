package br.com.rotaractsorocabaleste.rotasales.role;

import br.com.rotaractsorocabaleste.rotasales.common.entity.Auditable;
import br.com.rotaractsorocabaleste.rotasales.userrole.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class Role extends Auditable implements Serializable {

    private static final long serialVersionUID = 3699579303127525178L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    @NotNull
    private String description;
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserRole> users;

}
