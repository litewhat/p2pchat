package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		) {
			System.out.println("Open connection with: " + clientSocket.getInetAddress());
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				out.println(inputLine);
				application.getMessagePanel().getConversationTextArea().append(
						"client at time\n------\n" + inputLine + "\n-----\n");
			}
		} catch (IOException e) {
			System.out.println(
					"Exception caught when trying to listen on port " + portNumber + 
					" or listening for a connection");
			System.out.println(e.getMessage());
		} finally {
			System.out.println("Connection closed.");
		}
	}
}
