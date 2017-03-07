package pdfmark;

import java.io.File;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineNode;

public class PdfMarker {
	public void mark(String content, String inFile, String outFile,
			int startPage) throws Exception {
		// load doc
		PDDocument doc = PDDocument.load(new File(inFile));
		PDDocumentOutline outline = new PDDocumentOutline();
		doc.getDocumentCatalog().setDocumentOutline(outline);
		// add bookmarks
		startPage -= 2;
		String[] lines = content.trim().split("\n");
		Pattern p = Pattern.compile("(.*?)(\\d+)$");
		Stack<PDOutlineNode> nodeStack = new Stack<PDOutlineNode>();
		nodeStack.push(outline);
		int curTabs = 0;
		for (String s : lines) {
			Matcher m = p.matcher(s);
			if (m.find()) {
				String title = m.group(1).trim();
				int page = Integer.valueOf(m.group(2)) + startPage;
				int tabs = numTabs(s);
				PDOutlineItem bookmark = newBookmark(doc, title, page);
				if (tabs > curTabs) {
					curTabs = tabs;
					nodeStack.push(nodeStack.peek().getLastChild());
				} else if (tabs < curTabs) {
					curTabs = tabs;
					nodeStack.pop();
				}
				nodeStack.peek().addLast(bookmark);
			}
		}
		// save doc
		doc.save(outFile);
		doc.close();
	}

	private PDOutlineItem newBookmark(PDDocument doc, String title, int page) {
		PDOutlineItem mark = new PDOutlineItem();
		mark.setTitle(title);
		mark.setDestination(doc.getPage(page));
		return mark;
	}

	private int numTabs(String s) {
		for (int k = 0; k < s.length(); k++) {
			if (s.charAt(k) != '\t') {
				return k;
			}
		}
		return s.length();
	}
}
