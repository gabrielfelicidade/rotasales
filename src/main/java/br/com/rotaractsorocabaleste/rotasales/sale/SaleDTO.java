package br.com.rotaractsorocabaleste.rotasales.sale;

import br.com.rotaractsorocabaleste.rotasales.event.Event;
import br.com.rotaractsorocabaleste.rotasales.saleitem.SaleItem;
import br.com.rotaractsorocabaleste.rotasales.user.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class SaleDTO implements Serializable {

    private static final long serialVersionUID = 6372209561848660547L;

    private UUID id;
    @NotNull
    private String buyer;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User seller;
    @NotNull
    private Event event;
    @NotNull
    private Boolean donation;
    @Builder.Default
    @NotNull
    private SaleStatus status = SaleStatus.AWAITING_SEPARATION;
    @Builder.Default
    @NotNull
    private Boolean active = Boolean.TRUE;
    @NotEmpty
    private Set<SaleItem> items;

}
