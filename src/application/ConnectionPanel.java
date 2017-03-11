package application;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ConnectionPanel extends JPanel {
	
	private Application application;
	private JLabel hostNameLabel;
	private JTextField hostNameTextField;
	private JLabel portNumberLabel;
	private JTextField portNumberTextField;
	private JButton connectButton;
	private JButton disconnectButton;

	public ConnectionPanel(Application app) {
		super();
		this.application = app;
		setLayout(new GridBagLayout());
		build();
	}
	
	private void build() {
		GridBagConstraints constraints = new GridBagConstraints();
		
		hostNameLabel = new JLabel("Host:");
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.ipadx = 30;
		add(hostNameLabel, constraints);
		
		hostNameTextField = new JTextField("localhost");
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.ipadx = 200;
		add(hostNameTextField, constraints);
		
		portNumberLabel = new JLabel("Port:");
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.ipadx = 30;
		add(portNumberLabel, constraints);
		
		portNumberTextField = new JTextField("port");
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.ipadx = 200;
		add(portNumberTextField, constraints);
		
		connectButton = new JButton("Connect");
		connectButton.addActionListener(application);
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.ipadx = 0;
		add(connectButton, constraints);
		
		disconnectButton = new JButton("Disconnect");
		disconnectButton.addActionListener(application);
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.ipadx = 0;
		add(disconnectButton, constraints);
	}

	public JButton getConnectButton() {
		return connectButton;
	}
	
	public JButton getDisconnectButton() {
		return disconnectButton;
	}
}
