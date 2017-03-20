package application;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class ListPanel extends JPanel {
	
	private Application application;
	private JList list;
	private DefaultListModel listModel;
	private JScrollPane listScrollPane;

	public ListPanel(Application app) {
		super();
		this.application = app;
		setLayout(new GridBagLayout());
		build();
	}

	private void build() {
		GridBagConstraints constraints = new GridBagConstraints();
		listModel = new DefaultListModel();
		list = new JList(listModel);
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList list = (JList) evt.getSource();
				if (evt.getClickCount() == 2) {
					int index = list.locationToIndex(evt.getPoint());
					System.out.println(index);
				}
			}
		});
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listScrollPane = new JScrollPane(list);
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.ipadx = 200;
		constraints.ipady = 300;
		add(listScrollPane, constraints);
	}
	
	public DefaultListModel getListModel(){
		return listModel;
	}
}
