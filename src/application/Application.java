package application;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import main.Client;
import main.Message;
import main.Server;

public class Application extends JFrame implements ActionListener {
	
	private Server server;
	private Thread serverThread;
	private Client client;
	private Thread clientThread;
	
	private Message message;
	
	private ConnectionPanel connectionPanel;
	private MessagePanel messagePanel;
	private ListPanel listPanel;
	
	public Application() {
		super("Chat v0.0.1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridBagLayout());
		
		buildConnectionPanel();
		buildMessagePanel();
		buildListPanel();
		
		// You can choose on what port server will be listening to.
		String port = showInputPort();
				
		server = new Server(Integer.parseInt(port), this);
		serverThread = new Thread(server);
		serverThread.start();
		
		message = new Message("");		

		pack();
		setLocationToCenter();
		setVisible(true);
		setResizable(false);
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source.equals(messagePanel.getSendButton())) {
			String time = LocalDateTime.now().toString();
			message.setText(messagePanel.getMessageTextField().getText());
			messagePanel.getConversationTextArea().append("You at " + time + ":\n------\n" + message.getText() + "\n-----\n");
			new Thread(() -> {
				synchronized (message) {
					message.notify();
				}
			}).start();
			
		} else if (source.equals(connectionPanel.getConnectButton())) {
			String hostName = connectionPanel.getHostNameTextField().getText();
			int portNumber = Integer.parseInt(connectionPanel.getPortNumberTextField().getText());
			String element = hostName + " : " + portNumber;
			if (!listPanel.getListModel().contains(element)) {
				listPanel.getListModel().addElement(element);
			}
			client = new Client(hostName, portNumber, this, message);
			clientThread = new Thread(client);
			clientThread.start();
		} else if (source.equals(connectionPanel.getDisconnectButton())) {
			messagePanel.clearMessages();
			clientThread.interrupt();
			client = null;
		}
	}
	
	private void setLocationToCenter() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width  = (int) screenSize.getWidth() / 2;
		int height = (int) screenSize.getHeight() / 2;
		int x = width - getWidth() / 2;
		int y = height - getHeight() /2;
		super.setLocation(x, y);
	}
	
	private void buildConnectionPanel() {
		connectionPanel = new ConnectionPanel(this);
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(10, 10, 10, 10);
		add(connectionPanel, constraints);
}
	
	private void buildMessagePanel() {
		messagePanel = new MessagePanel(this);
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.insets = new Insets(10, 10, 10, 10);
		add(messagePanel, constraints);
	}
	
	private void buildListPanel() {
		listPanel = new ListPanel(this);
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.gridheight = 2;
		constraints.insets = new Insets(10, 10, 10, 10);
		add(listPanel, constraints);
	}


	public MessagePanel getMessagePanel() {
		return messagePanel; 
	}
	
	public Client getClient() {
		return client;
	}
	
	private String showInputPort() {
		return JOptionPane.showInputDialog(
					this,
					"Type port number:",
					"Start server...",
					JOptionPane.PLAIN_MESSAGE);
	}

	public boolean showShouldAcceptConnectionMessage() {
		int result = JOptionPane.showOptionDialog(
							this,
							"Do you want to accept connection from ...",
							"Connection",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE,
							null,
							null,
							null);
		if (result == 0)
			return true;
		return false;
	}
}
