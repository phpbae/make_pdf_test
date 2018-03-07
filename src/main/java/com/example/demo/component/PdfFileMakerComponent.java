package com.example.demo.component;

import be.quodlibet.boxable.BaseTable;
import be.quodlibet.boxable.Cell;
import be.quodlibet.boxable.Row;
import be.quodlibet.boxable.datatable.DataTable;
import com.example.demo.domain.Member;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class PdfFileMakerComponent {

    private PDDocument document;
    private PDPage pdPage;
    private PDPageContentStream contentStream;
    private PDFont font = PDType1Font.HELVETICA_BOLD;
    private PDFont hangulFont = null;

    private static int pageCount = 1;

    private void initPDF() throws IOException {
        document = new PDDocument();
        pdPage = new PDPage(PDRectangle.A4);
        document.addPage(pdPage);
        pdPage.setMediaBox(new PDRectangle(PDRectangle.A4.getHeight(), PDRectangle.A4.getWidth()));
        hangulFont = PDType0Font.load(document, new File("c:/Windows/Fonts/malgun.TTF"));
    }


    public File executeCreatePDF(List<Member> members) {
        File pdfFile = new File(pageCount + ".pdf");

        try {
            initPDF();
            float margin = 10;
            float tableWidth = pdPage.getMediaBox().getWidth() - (2 * margin);
            float yStartNewPage = pdPage.getMediaBox().getHeight() - (2 * margin);
            float yStart = yStartNewPage;
            float bottomMargin = 20;


            List<List> data = new ArrayList();
            data.add(new ArrayList<>(Arrays.asList("Column One", "Column Two", "123123", "phpbae", "no hangul")));
            for (int i = 0; i < members.size(); i++) {
                data.add(new ArrayList<>(Arrays.asList(" Col One", "tid : " + members.get(i).getTid(), "Row " + i + " Col Three", "Row " + i + " Col Four", "Row " + i + " Col Five")));
                BaseTable dataTable = new BaseTable(yStart, yStartNewPage, bottomMargin, tableWidth, margin, document, pdPage, true, true);
                DataTable t = new DataTable(dataTable, pdPage);
                t.addListToTable(data, DataTable.HASHEADER);
                dataTable.draw();
            }

            document.save(pdfFile);
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        pageCount += 1;
        return pdfFile;
    }

    public File testCreatePDF(Member member) {
        File pdfFile = new File("test.pdf");

        try {
            initPDF();
            contentStream = new PDPageContentStream(document, pdPage);

            contentStream.setLeading(10 * 1.2);
            contentStream.setLineWidth(1);
            contentStream.moveTo(35, 744);
            contentStream.lineTo(562, 744);
            contentStream.stroke();

            contentStream.beginText();
            contentStream.setFont(hangulFont, 10);
            contentStream.newLineAtOffset(35, 820);
            contentStream.showText("Test PDF Text");
            contentStream.newLine();
            contentStream.showText(String.valueOf(member.getTid()));
            contentStream.showText(" ");
            contentStream.showText(String.valueOf(member.getMemberName()));
            contentStream.showText(" ");
            contentStream.showText(member.getBirthYear());
            contentStream.showText(" ");
            contentStream.showText(member.getBirthDate());
            contentStream.showText(" ");
            contentStream.showText(member.getEtc());
            contentStream.showText(" ");
            contentStream.showText(member.getEtc1());
            contentStream.showText(" ");
            contentStream.showText(member.getEtc2());
            contentStream.showText(" ");
            contentStream.showText(member.getEtc3());
            contentStream.showText(" ");
            contentStream.showText(member.getEtc4());
            contentStream.showText(" ");
            contentStream.showText(member.getMemberAddress1());
            contentStream.endText();

            contentStream.close();
            document.save(pdfFile);
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pdfFile;
    }


}
