package br.com.rotaractsorocabaleste.rotasales.sale;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ChangeSaleStatusDTO implements Serializable {

    private static final long serialVersionUID = 447806007342137632L;

    @NotNull
    private UUID id;
    @NotNull
    private SaleStatus status;

}
