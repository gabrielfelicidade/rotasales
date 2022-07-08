package br.com.rotaractsorocabaleste.rotasales.event;

import br.com.rotaractsorocabaleste.rotasales.common.entity.Auditable;
import br.com.rotaractsorocabaleste.rotasales.institution.Institution;
import br.com.rotaractsorocabaleste.rotasales.item.Item;
import br.com.rotaractsorocabaleste.rotasales.sale.Sale;
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
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Entity
@Getter
@NoArgsConstructor
@Setter
@With
public class Event extends Auditable implements Serializable {

    private static final long serialVersionUID = 1283739019708786707L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    @NotNull
    private String title;
    private String description;
    @Column(nullable = false)
    @NotNull
    private LocalDateTime date;
    @Column(nullable = false)
    @NotNull
    private LocalDateTime startSales;
    @Column(nullable = false)
    @NotNull
    private LocalDateTime endSales;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(optional = false)
    @NotNull
    private Institution institution;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private EventSalesStrategy salesStrategy;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Item> items;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Sale> sales;

}
