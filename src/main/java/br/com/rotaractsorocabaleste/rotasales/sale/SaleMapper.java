package br.com.rotaractsorocabaleste.rotasales.sale;

import br.com.rotaractsorocabaleste.rotasales.saleitem.SaleItemMapper;

import java.math.BigDecimal;
import java.util.stream.Collectors;

public class SaleMapper {

    public static Sale toSale(final SaleDTO saleDTO) {
        return Sale.builder()
                .active(saleDTO.getActive())
                .buyer(saleDTO.getBuyer())
                .donation(saleDTO.getDonation())
                .event(saleDTO.getEvent())
                .id(saleDTO.getId())
                .items(saleDTO.getItems())
                .seller(saleDTO.getSeller())
                .status(saleDTO.getStatus())
                .build();
    }

    public static SaleDTO toSaleDTO(final Sale sale) {
        return SaleDTO.builder()
                .active(sale.getActive())
                .buyer(sale.getBuyer())
                .donation(sale.getDonation())
                .event(sale.getEvent())
                .id(sale.getId())
                .items(sale.getItems())
                .seller(sale.getSeller())
                .status(sale.getStatus())
                .build();
    }

    public static GetSalesVO toGetSalesVO(final Sale sale) {
        return GetSalesVO.builder()
                .buyer(sale.getBuyer())
                .donation(sale.getDonation())
                .event(sale.getEvent().getTitle())
                .id(sale.getId())
                .status(sale.getStatus())
                .totalValue(sale.getItems()
                                .stream()
                                .map(saleItem -> saleItem.getAmount().multiply(saleItem.getItem().getValue()))
                                .reduce(BigDecimal.ZERO, BigDecimal::add))
                .items(sale.getItems()
                        .stream()
                        .map(SaleItemMapper::toSaleItemVO)
                        .collect(Collectors.toSet()))
                .build();
    }

}
