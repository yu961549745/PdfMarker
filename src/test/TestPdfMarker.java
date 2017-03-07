package test;

import pdfmark.FileUtils;
import pdfmark.PdfMarker;

public class TestPdfMarker {
	public static void main(String[] args) throws Exception {
		PdfMarker marker = new PdfMarker();
		marker.mark(FileUtils.read("bookmark.txt"), "test2.pdf", "res2.pdf", 15);
	}
}
