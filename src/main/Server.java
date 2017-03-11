package main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

import application.Application;

public class Server implements Runnable {
	
	private Application application;
	private int portNumber;
	
	public Server(int port, Application app) {
		this.portNumber = port;
		this.application = app;
	}

	@Override
	public void run() {
		while (true) {
			try (
				ServerSocket serverSocket = new ServerSocket(portNumber);
				Socket clientSocket = serverSocket.accept();
				ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
			) {
				String address = clientSocket.getInetAddress().getHostAddress();
				System.out.println("Open connection with: " + address);
				while (true) {
					String message = (String) in.readObject();
					String time = LocalDateTime.now().toString();
					System.out.println(message);
					out.writeBytes(message);
					application.getMessagePanel().getConversationTextArea().append(
							address + " at " + time + ":\n------\n" + message + "\n-----\n");
				}
			} catch (IOException e) {
				System.out.println(
						"Exception caught when trying to listen on port " + portNumber + 
						" or listening for a connection");
				System.out.println(e.getMessage());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				System.out.println("Connection closed.");
			}
		}
	}
}
