package pdfmark;

import java.io.BufferedReader;
import java.io.FileReader;

public class FileUtils {
	public static String read(String filename) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		StringBuffer buffer = new StringBuffer();
		while (reader.ready()) {
			buffer.append(reader.readLine()+"\n");
		}
		reader.close();
		return buffer.toString();
	}
}
