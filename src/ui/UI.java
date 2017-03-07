package ui;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import pdfmark.PdfMarker;

public class UI {
	private JFrame frame = new JFrame("PDF Marker");
	private String[] inputs = new String[] { "Input PDF File",
			"Output PDF File", "Start Page Index" };
	private InputPanel inputPanel = new InputPanel(inputs, 100);
	private JTextArea contentInput = new JTextArea(20, 100);
	private JButton actionButton = new JButton("DO");

	private PdfMarker marker = new PdfMarker();

	public UI() {
		frame.setLayout(new BorderLayout());
		frame.add(inputPanel.getPanel(), BorderLayout.NORTH);
		JScrollPane scrollPane = new JScrollPane(contentInput);
		frame.add(scrollPane, BorderLayout.CENTER);
		frame.add(actionButton, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

		actionButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String content = contentInput.getText();
				String inFile = inputPanel.getInput(inputs[0]).getText();
				String outFile = inputPanel.getInput(inputs[1]).getText();
				int startPage = Integer.valueOf(inputPanel.getInput(inputs[2])
						.getText());
				try {
					marker.mark(content, inFile, outFile, startPage);
					Desktop.getDesktop().open(new File(outFile));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		new UI();
	}
}
