package br.com.rotaractsorocabaleste.rotasales.sale;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.util.UUID;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;

    @GetMapping
    public Page<GetSalesVO> findMySales(@RequestParam(required = false) final UUID eventId,
                                        @RequestParam(required = false) final String buyer,
                                        @RequestParam(required = false) final Boolean donation,
                                        @RequestParam(required = false, defaultValue = "0") final int page,
                                        @RequestParam(required = false, defaultValue = "10") final int limit) {
        return saleService.findMySales(eventId, buyer, donation, page, limit);
    }

    @PostMapping
    public SaleDTO create(@RequestBody @Valid final SaleDTO saleDTO) {
        return saleService.create(saleDTO);
    }

    @GetMapping(value = "/receipt/{saleId}", produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] getSaleReceiptInPdf(@PathVariable final UUID saleId) {
        return saleService.getSaleReceiptInPdfStream(saleId);
    }

    @GetMapping("/{saleId}")
    public SaleDTO findSaleById(@PathVariable final UUID saleId) {
        return saleService.findDTOById(saleId);
    }

    @PutMapping
    public void updateSale(@RequestBody @Valid final SaleDTO saleDTO) {
        saleService.updateSale(saleDTO);
    }

    @PatchMapping
    public void changeSaleStatus(@RequestBody @Valid final ChangeSaleStatusDTO changeSaleStatusDTO) {
        saleService.changeSaleStatus(changeSaleStatusDTO);
    }

    @DeleteMapping("/{saleId}")
    public void deleteById(@PathVariable final UUID saleId) {
        saleService.deleteById(saleId);
    }

}
