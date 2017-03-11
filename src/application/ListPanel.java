package application;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ListPanel extends JPanel {
	
	private Application application;
	private JList list;
	private JScrollPane listScrollPane;

	public ListPanel(Application app) {
		super();
		this.application = app;
		setLayout(new GridBagLayout());
		build();
	}

	private void build() {
		GridBagConstraints constraints = new GridBagConstraints();
		list = new JList();
		listScrollPane = new JScrollPane(list);
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.ipadx = 200;
		constraints.ipady = 300;
		add(listScrollPane, constraints);
	}
}
