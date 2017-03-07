package test;

import pdfmark.FileUtils;
import pdfmark.PdfMarker;

public class TestPdfMarker {
	public static void main(String[] args) throws Exception {
		PdfMarker marker = new PdfMarker();
		marker.mark(FileUtils.read("./test/bookmark.txt"), "./test/test.pdf",
				"./test/res.pdf", 15);
	}
}
