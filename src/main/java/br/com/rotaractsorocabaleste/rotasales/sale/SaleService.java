package br.com.rotaractsorocabaleste.rotasales.sale;

import org.springframework.data.domain.Page;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

public interface SaleService {

    Page<GetSalesVO> findMySales(final UUID event,
                                 final String buyer,
                                 final Boolean donation,
                                 final int page,
                                 final int limit);

    SaleDTO create(final SaleDTO saleDTO);

    void deleteById(final UUID saleId);

    SaleDTO findDTOById(final UUID saleId);

    void updateSale(final SaleDTO saleDTO);

    void changeSaleStatus(final ChangeSaleStatusDTO changeSaleStatusDTO);

    byte[] getSaleReceiptInPdfStream(final UUID saleId);
}
