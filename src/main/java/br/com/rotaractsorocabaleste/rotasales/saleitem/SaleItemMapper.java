package br.com.rotaractsorocabaleste.rotasales.saleitem;

public class SaleItemMapper {

    public static SaleItemVO toSaleItemVO(final SaleItem saleItem) {
        return SaleItemVO.builder()
                .amount(saleItem.getAmount())
                .name(saleItem.getItem().getName())
                .build();
    }

}
