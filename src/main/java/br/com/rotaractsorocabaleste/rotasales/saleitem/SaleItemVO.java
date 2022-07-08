package br.com.rotaractsorocabaleste.rotasales.saleitem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class SaleItemVO implements Serializable {

    private static final long serialVersionUID = 9135001693007005599L;

    private String name;
    private BigDecimal amount;

}
