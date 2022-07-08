package br.com.rotaractsorocabaleste.rotasales.sale;

import br.com.rotaractsorocabaleste.rotasales.common.entity.Auditable;
import br.com.rotaractsorocabaleste.rotasales.event.Event;
import br.com.rotaractsorocabaleste.rotasales.saleitem.SaleItem;
import br.com.rotaractsorocabaleste.rotasales.user.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
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
public class Sale extends Auditable implements Serializable {

    private static final long serialVersionUID = 3952974627963927892L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    @NotNull
    private String buyer;
    @ManyToOne(optional = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User seller;
    @ManyToOne(optional = false)
    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Event event;
    @Column(nullable = false)
    @NotNull
    private Boolean donation;
    @Builder.Default
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SaleStatus status = SaleStatus.AWAITING_SEPARATION;
    @Builder.Default
    @Column(nullable = false)
    private Boolean active = Boolean.TRUE;
    @NotEmpty
    @OneToMany(mappedBy = "sale", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<SaleItem> items;

}
