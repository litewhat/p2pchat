package application;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.Server;

public class Application extends JFrame implements ActionListener {
	
	private Server server;
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
		server = new Server(20000, this);
		new Thread(server).start();

		pack();
		setLocationToCenter();
		setVisible(true);
		setResizable(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source.equals(messagePanel.getSendButton())) {
			String message = messagePanel.getMessageTextField().getText();
			System.out.println("Send -> hostName:portNumber");
			System.out.println(message);
			messagePanel.getConversationTextArea().append("you at time\n------\n" + message + "\n-----\n");
		} else if (source.equals(connectionPanel.getConnectButton())) {
			System.out.println("Connect button pressed!");

		} else if (source.equals(connectionPanel.getDisconnectButton())) {
			System.out.println("Disconnect button pressed!");
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



}
