package br.com.rotaractsorocabaleste.rotasales.sale;

import br.com.rotaractsorocabaleste.rotasales.saleitem.SaleItemVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class GetSalesVO implements Serializable {

    private static final long serialVersionUID = -7887898120806385201L;

    private UUID id;
    private String event;
    private String buyer;
    private BigDecimal totalValue;
    private Boolean donation;
    private SaleStatus status;
    private Set<SaleItemVO> items;

}
