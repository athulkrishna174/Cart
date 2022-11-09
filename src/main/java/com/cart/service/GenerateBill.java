package com.cart.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import com.cart.model.Item;
import com.cart.model.User;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class GenerateBill {
	
	
	private static final String PATH = "C:\\Users\\athul\\Downloads\\Ecs_Training\\Assessment_3\\Bill.pdf";
	
	private static final Font TITLE_FONT = FontFactory.getFont(FontFactory.HELVETICA, 15, BaseColor.RED);
	private static final Font DATE_FONT = FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 8, BaseColor.BLACK);
	private static final Font CONTENS_FONT = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK);
	private static final Font GRAY_FONT = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.GRAY);
	private static final Font TABLE_HEADER_FONT = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8, BaseColor.BLACK);
	private static final Font TABLE_CONTENT_FONT = FontFactory.getFont(FontFactory.HELVETICA, 8, BaseColor.BLACK);
	private static final Font TOTAL_AMOUNT_FONT = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8, BaseColor.DARK_GRAY);

	
	public void generateBill(User user)throws FileNotFoundException, DocumentException, SQLException {
		
		CartService cartService = new CartService();
		List<Item> items = cartService.getItems(user.getId());
		
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(PATH));
		
		document.open();
		
		setTitle(document);
		setBilldate(document);
		setUserDetailds(document, user);
		setItemsTable(document, items);
		
		Paragraph emptySpace = new Paragraph();
		addEmptyLine(emptySpace, 1);
		document.add(emptySpace);
		
		setTotalAmount(document, items);
		
		cartService.updateMyOrder(user.getId());
		cartService.deleteAllItems(user.getId());
		
		document.close();
	}
	
	private void setTitle(Document document) throws DocumentException {
		Paragraph title = new Paragraph("INVOICE", TITLE_FONT);
		title.setAlignment(Element.ALIGN_CENTER);
		
		addEmptyLine(title, 1);
		
		document.add(title);
	}
	
	private void setBilldate(Document document)throws DocumentException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
	    Date date = new Date(); 
	    
	    Paragraph billDate = new Paragraph("Bill Date: " + formatter.format(date), DATE_FONT);
	    addEmptyLine(billDate, 2);
	    
		document.add(billDate);
	}
	
	private void setUserDetailds(Document document,User user) throws DocumentException {
		Paragraph content = new Paragraph();
		
		content.add(new Paragraph("Billed To", GRAY_FONT));
		content.add(new Paragraph("Name: " + user.getName(), CONTENS_FONT));
		content.add(new Paragraph("Contact Number: " + user.getPhone(), CONTENS_FONT));
		content.add(new Paragraph("Email: " + user.getEmail(), CONTENS_FONT));
		content.add(new Paragraph("Shipping Address: " + user.getAddress(), CONTENS_FONT));
		content.add(new Paragraph("Card Number: " + user.getCardNo(), CONTENS_FONT));
		
		addEmptyLine(content, 2);
		
		document.add(content);
	}
	
	private void setItemsTable(Document document, List<Item> items) throws DocumentException {
		PdfPTable table = new PdfPTable(4);
		addTableHeader(table);
		
		table.setTotalWidth(520f);
		table.setLockedWidth(true);
		
		float[] columnWidths = new float[]{30f, 20f, 20f, 30f};
		table.setWidths(columnWidths);

		for(Item item : items) {
			addTableItems(table, item);
		}
		
		document.add(table);
	}
	
	private void addTableHeader(PdfPTable table) {
	    Stream.of("Product", "Unit Cost", "Quantity", "Amount")
	      .forEach(columnTitle -> {
	        PdfPCell header = new PdfPCell();
	        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
	        header.setBorderWidth(1);
	        header.setFixedHeight(15f);
	        header.setHorizontalAlignment(Element.ALIGN_CENTER);
	        header.setPhrase(new Phrase(columnTitle, TABLE_HEADER_FONT));
	        table.addCell(header);
	    });
	}
	
	private void addTableItems(PdfPTable table, Item item) {
	    Stream.of(item.getName(), String.valueOf(item.getPrice()), 
	    		String.valueOf(item.getQuantity()), 
	    		String.valueOf(item.getTotal()))
	      .forEach(columnTitle -> {
	        PdfPCell header = new PdfPCell();
	        header.setBorderWidth(1);
	        header.setFixedHeight(15f);
	        header.setHorizontalAlignment(Element.ALIGN_CENTER);
	        header.setPhrase(new Phrase(columnTitle, TABLE_CONTENT_FONT));
	        table.addCell(header);
	    });
	}
	
	private void setTotalAmount(Document document, List<Item> items) throws DocumentException {
		int total = 0;
		
		for(Item item : items) {
			total += item.getTotal();
		}
		
		Paragraph totalAmount = new Paragraph("GRAND TOTAL: " + total, TOTAL_AMOUNT_FONT);
		
		totalAmount.setAlignment(Element.ALIGN_RIGHT);
		
		document.add(totalAmount);
	}

	private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
