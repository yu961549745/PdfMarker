package pdfmark;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;

public class Test {
	public static void main(String[] args) throws Exception {
		PDDocument doc = PDDocument.load(new File("test.pdf"));
		PDDocumentOutline outline = new PDDocumentOutline();
		doc.getDocumentCatalog().setDocumentOutline(outline);
		String titles = FileUtils.read("bookmark.txt");
		int startPage = 7 - 2;
		Pattern p = Pattern.compile("(?m)(.*?)(\\d+)$");
		Matcher m = p.matcher(titles);
		while (m.find()) {
			String title = m.group(1).trim();
			int page = Integer.valueOf(m.group(2));
			System.out.println(title + " " + (startPage + page));
			PDOutlineItem mark = new PDOutlineItem();
			mark.setTitle(title);
			mark.setDestination(doc.getPage(startPage + page));
			outline.addLast(mark);
		}
		doc.save("res.pdf");
		doc.close();
	}
}
