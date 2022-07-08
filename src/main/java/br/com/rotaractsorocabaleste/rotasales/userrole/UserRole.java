package br.com.rotaractsorocabaleste.rotasales.userrole;

import br.com.rotaractsorocabaleste.rotasales.common.entity.Auditable;
import br.com.rotaractsorocabaleste.rotasales.role.Role;
import br.com.rotaractsorocabaleste.rotasales.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Entity
@Getter
@NoArgsConstructor
@Setter
public class UserRole extends Auditable implements Serializable {

    private static final long serialVersionUID = 3964940587823608375L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne(optional = false)
    @NotNull
    private User user;
    @ManyToOne(optional = false)
    @NotNull
    private Role role;

}
