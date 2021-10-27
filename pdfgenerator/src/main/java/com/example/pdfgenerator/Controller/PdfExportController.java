package com.example.pdfgenerator.Controller;

import com.example.pdfgenerator.Services.PdfGeneratorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class PdfExportController {
    private final PdfGeneratorService pdfGeneratorService;

    public PdfExportController(PdfGeneratorService pdfGeneratorService) {
        this.pdfGeneratorService = pdfGeneratorService;
    }

    @GetMapping("/pdf/generate")
    public void generatePDF(HttpServletResponse httpServletResponse) throws IOException {
      httpServletResponse.setContentType("application/pdf");
        DateFormat dateFormater = new SimpleDateFormat("yyyy-mm-dd:hh:mm:ss");
        String currintDateTime = dateFormater.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "Attachment: filename_" + currintDateTime + ".pdf";

        httpServletResponse.setHeader(headerKey, headerValue);

        this.pdfGeneratorService.export(httpServletResponse);
    }
}
