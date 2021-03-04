package br.com.rotaractsorocabaleste.rotasales.entrypoint.controller

import br.com.rotaractsorocabaleste.rotasales.core.service.SaleService
import com.itextpdf.barcodes.BarcodeQRCode
import com.itextpdf.io.image.ImageDataFactory
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Image
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.property.HorizontalAlignment
import com.itextpdf.layout.property.TextAlignment
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.ResourceLoader
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.util.FileCopyUtils
import org.springframework.util.ResourceUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.ByteArrayOutputStream
import java.util.*

@RestController
@RequestMapping("/exports")
class ExportController(
    private val saleService: SaleService,
    private val resourceLoader: ResourceLoader
) {

    @GetMapping("/receipt/{saleId}")
    fun exportReceiptToPdf(@PathVariable saleId: UUID): ResponseEntity<ByteArray> {
        val saleOptional = saleService.findById(saleId)
        if (saleOptional.isEmpty)
            return ResponseEntity.notFound().build()
        val sale = saleOptional.get()
        val byteArrayStream = ByteArrayOutputStream()
        val writer = PdfWriter(byteArrayStream)
        val pdf = PdfDocument(writer)
        val document = Document(pdf)
        val logoImage = Image(ImageDataFactory.create(FileCopyUtils.copyToByteArray(ClassPathResource("logo.png").inputStream)))
        logoImage.scaleToFit(342.0F, 121.0F)
        logoImage.setMarginBottom(30.0F)
        logoImage.setHorizontalAlignment(HorizontalAlignment.CENTER)
        document.add(logoImage)
        val clientParagraph = Paragraph("Cliente: ${sale.customer}")
        clientParagraph.setTextAlignment(TextAlignment.CENTER)
        document.add(clientParagraph)
        val qrcode = BarcodeQRCode(sale.id.toString())
        val qrcodeImage = Image(qrcode.createFormXObject(null, 7.0F, pdf))
        qrcodeImage.setHorizontalAlignment(HorizontalAlignment.CENTER)
        document.add(qrcodeImage)
        sale.items.forEach {
            val itemParagraph = Paragraph("${it.item?.description} - ${it.amount.intValueExact()}un");
            itemParagraph.setTextAlignment(TextAlignment.CENTER);
            document.add(itemParagraph)
        }
        document.close()
        pdf.close()
        writer.close()
        val headers = HttpHeaders()
        headers.add("Content-Disposition", "attachment; filename=report.pdf")
        return ResponseEntity.ok().headers(headers).body(byteArrayStream.toByteArray())
    }

}