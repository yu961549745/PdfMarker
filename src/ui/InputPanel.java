package ui;

import java.util.HashMap;
import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InputPanel {

	private static class InputPair {
		public JLabel label;
		public JTextField input;

		public InputPair(String title, int cols) {
			label = new JLabel(title);
			input = new JTextField(cols);
		}
	}

	private Map<String, InputPair> map = new HashMap<String, InputPair>();
	private JPanel panel = new JPanel();

	public InputPanel(String[] keys, int cols) {
		for (String key : keys) {
			map.put(key, new InputPair(key, cols));
		}
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		ParallelGroup gLabel = layout.createParallelGroup();
		ParallelGroup gInput = layout.createParallelGroup();
		for (String key : keys) {
			gLabel.addComponent(map.get(key).label);
			gInput.addComponent(map.get(key).input);
		}
		hGroup.addGroup(gLabel);
		hGroup.addGroup(gInput);
		layout.setHorizontalGroup(hGroup);

		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		for (String key : keys) {
			InputPair p = map.get(key);
			vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
					.addComponent(p.label).addComponent(p.input));
		}
		layout.setVerticalGroup(vGroup);
	}

	public JTextField getInput(String key) {
		return map.get(key).input;
	}

	public JPanel getPanel() {
		return panel;
	}

}
