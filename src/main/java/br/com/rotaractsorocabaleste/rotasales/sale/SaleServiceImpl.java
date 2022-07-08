package br.com.rotaractsorocabaleste.rotasales.sale;

import br.com.rotaractsorocabaleste.rotasales.common.validation.Validator;
import br.com.rotaractsorocabaleste.rotasales.saleitem.SaleItem;
import br.com.rotaractsorocabaleste.rotasales.saleitem.SaleItemService;
import br.com.rotaractsorocabaleste.rotasales.user.UserService;
import com.itextpdf.barcodes.BarcodeQRCode;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final UserService userService;
    private final SaleRepository saleRepository;
    private final SaleItemService saleItemService;
    private final Validator<Sale> saleValidator;

    @Override
    public Page<GetSalesVO> findMySales(final UUID eventId,
                                        final String buyer,
                                        final Boolean donation,
                                        final int page,
                                        final int limit) {
        final var user = userService.findLoggedInUser();
        final var pageable = PageRequest.of(page, limit);

        return findByUserAndEventAndBuyerAndIsDonationAndIsActive(user.getId(), eventId, buyer, donation, pageable)
                .map(SaleMapper::toGetSalesVO);
    }

    @Override
    public SaleDTO create(final SaleDTO saleDTO) {
        final var sale = SaleMapper.toSale(saleDTO);

        saleValidator.validate(sale);

        final var user = userService.findLoggedInUser();
        final var persistedSale = saleRepository.save(sale.withSeller(user));
        final var items = sale.getItems();
        items.forEach(saleItem -> saleItem.setSale(persistedSale));

        saleItemService.saveAll(items);

        return SaleMapper.toSaleDTO(persistedSale);
    }

    @Override
    public void deleteById(final UUID saleId) {
        if (!saleBelongsToLoggedInUser(saleId)) {
            throw new SaleNotBelongsToLoggedInUserException();
        }

        saleRepository.deleteById(saleId);
    }

    @Override
    public SaleDTO findDTOById(final UUID saleId) {
        return saleRepository.findById(saleId)
                .map(SaleMapper::toSaleDTO)
                .orElseThrow(SaleNotFoundException::new);
    }

    @Override
    public void updateSale(final SaleDTO saleDTO) {
        if (!saleBelongsToLoggedInUser(saleDTO.getId())) {
            throw new SaleNotBelongsToLoggedInUserException();
        }

        final var sale = findById(saleDTO.getId());

        saleItemService.deleteAllByIdInBatch(sale.getItems()
                .stream()
                .map(SaleItem::getId)
                .collect(Collectors.toSet()));

        create(saleDTO);
    }

    @Override
    public void changeSaleStatus(final ChangeSaleStatusDTO changeSaleStatusDTO) {
        final var sale = findById(changeSaleStatusDTO.getId());

        checkSaleStatusChangeIsValid(sale.getStatus(), changeSaleStatusDTO.getStatus());

        sale.setStatus(changeSaleStatusDTO.getStatus());

        saleRepository.save(sale);
    }

    @Override
    public byte[] getSaleReceiptInPdfStream(final UUID saleId) {
        final var sale = findById(saleId);
        final var outputStream = new ByteArrayOutputStream();

        try {
            final var writer = new PdfWriter(outputStream);
            final var pdf = new PdfDocument(writer);
            final var document = new Document(pdf);
            final var logoImageName = sale.getSeller().getInstitution().getId().toString().concat(".png");
            final var logoImage = new Image(ImageDataFactory.create(FileCopyUtils.copyToByteArray(new ClassPathResource(logoImageName).getInputStream())));
            logoImage.scaleToFit(342.0F, 121.0F);
            logoImage.setMarginBottom(30.0F);
            logoImage.setHorizontalAlignment(HorizontalAlignment.CENTER);
            document.add(logoImage);
            final var clientParagraph = new Paragraph("Comprador: ".concat(sale.getBuyer()));
            clientParagraph.setTextAlignment(TextAlignment.CENTER);
            document.add(clientParagraph);
            final var qrcode = new BarcodeQRCode(saleId.toString());
            final var qrcodeImage = new Image(qrcode.createFormXObject(null, 7.0F, pdf));
            qrcodeImage.setHorizontalAlignment(HorizontalAlignment.CENTER);
            document.add(qrcodeImage);
            sale.getItems().forEach(item -> {
                final var itemParagraph = new Paragraph(item.getItem().getName().concat(" - ").concat(item.getAmount().toString()));
                itemParagraph.setTextAlignment(TextAlignment.CENTER);
                document.add(itemParagraph);
            });
            document.close();
            pdf.close();
            writer.close();
        } catch (final IOException e) {
            throw new UnexpectedErrorException(e.getMessage());
        }


        return outputStream.toByteArray();
    }

    private void checkSaleStatusChangeIsValid(final SaleStatus saleStatus, final SaleStatus newSaleStatus) {
        if (newSaleStatus.ordinal() <= saleStatus.ordinal()) {
            throw new InvalidSaleStatusChangeException();
        }
    }

    private boolean saleBelongsToLoggedInUser(final UUID saleId) {
        final var sale = findById(saleId);
        final var user = userService.findLoggedInUser();

        return user.getId().equals(sale.getSeller().getId());
    }

    private Sale findById(final UUID saleId) {
        return saleRepository.findById(saleId)
                .orElseThrow(SaleNotFoundException::new);
    }

    private Page<Sale> findByUserAndEventAndBuyerAndIsDonationAndIsActive(final UUID userId,
                                                                          final UUID eventId,
                                                                          final String buyer,
                                                                          final Boolean donation,
                                                                          final Pageable pageable) {
        return saleRepository.findByUserAndEventAndBuyerAndIsDonationAndIsActive(userId, eventId, buyer, donation, pageable);
    }

}
