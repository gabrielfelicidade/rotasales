package br.com.rotaractsorocabaleste.rotasales.saleitem;

import br.com.rotaractsorocabaleste.rotasales.common.entity.Auditable;
import br.com.rotaractsorocabaleste.rotasales.item.Item;
import br.com.rotaractsorocabaleste.rotasales.sale.Sale;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Entity
@Getter
@NoArgsConstructor
@Setter
public class SaleItem  extends Auditable implements Serializable {

    private static final long serialVersionUID = 3964940587823608375L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    @NotNull
    private BigDecimal amount;
    @ManyToOne(optional = false)
    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Sale sale;
    @ManyToOne(optional = false)
    @NotNull
    private Item item;

}
