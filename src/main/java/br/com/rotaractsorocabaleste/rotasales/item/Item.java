package br.com.rotaractsorocabaleste.rotasales.item;

import br.com.rotaractsorocabaleste.rotasales.common.entity.Auditable;
import br.com.rotaractsorocabaleste.rotasales.event.Event;
import br.com.rotaractsorocabaleste.rotasales.saleitem.SaleItem;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Entity
@Getter
@NoArgsConstructor
@Setter
@With
public class Item extends Auditable implements Serializable {

    private static final long serialVersionUID = 7478207859450256246L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    @NotNull
    private String name;
    @Column(nullable = false)
    @NotNull
    private BigDecimal value;
    @ManyToOne(optional = false)
    @NotNull
    private Event event;
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<SaleItem> sales;

}
