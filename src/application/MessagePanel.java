/**
 * <h1>Message Panel</h1>
 * This class specifies content and grid of message panel in application uses it.
 * 
 * @author Paweł Zielonka
 * @version 1.0
 */
package application;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.PrintStream;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MessagePanel extends JPanel {
	
	private Application application;
	private JTextArea conversationTextArea;
	private JScrollPane conversationScrollPane;
	private JTextArea messageTextField;
	private JScrollPane messageScrollPane;
	private JButton sendButton;

	public MessagePanel(Application app) {
		super();
		this.application = app;
		setLayout(new GridBagLayout());
		build();
	}
	
	private void build() {
		GridBagConstraints constraints = new GridBagConstraints();
		
		conversationTextArea = new JTextArea();
		conversationTextArea.setEditable(false);
		conversationTextArea.setLineWrap(true);
		conversationScrollPane = new JScrollPane(conversationTextArea);
		conversationScrollPane.setAutoscrolls(true);
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridheight = 6;
		constraints.ipady = 300;
		constraints.ipadx = 250;
		add(conversationScrollPane, constraints);
		
		messageTextField = new JTextArea("Message");
		messageScrollPane = new JScrollPane(messageTextField);
		constraints.gridx = 0;
		constraints.gridy = 7;
		constraints.gridheight = 3;
		constraints.ipady = 50;
		constraints.ipadx = 250;
		add(messageScrollPane, constraints);
		
		sendButton = new JButton("Send");
		sendButton.addActionListener(application);
		constraints.gridx = 0;
		constraints.gridy = 10;
		constraints.gridheight = 1;
		constraints.ipady = 0;
		constraints.ipadx = 0;
		add(sendButton, constraints);
	}

	

	public JTextArea getConversationTextArea() {
		return conversationTextArea;
	}

	public JButton getSendButton() {
		return sendButton;
	}

	public JTextArea getMessageTextField() {
		return messageTextField;
	}

	public void clearMessages() {
		conversationTextArea.setText("");
		
	}
}
