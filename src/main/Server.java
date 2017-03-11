package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

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
		try (
			ServerSocket serverSocket = new ServerSocket(portNumber);
			Socket clientSocket = serverSocket.accept();
			ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
		) {
			System.out.println("Open connection with: " + clientSocket.getInetAddress());
			while (true) {
				String message = (String) in.readObject();
				System.out.println(message);
				out.writeBytes(message);
				application.getMessagePanel().getConversationTextArea().append(
						"client at time\n------\n" + message + "\n-----\n");
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
