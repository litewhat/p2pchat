package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import application.Application;

public class Client implements Runnable {
	
	private Application application;
	private int portNumber;
	private String hostName;
	private Message message;
	private boolean connected;
	
	public Client(String host, int port, Application app, Message message) {
		this.hostName = host;
		this.portNumber = port;
		this.application = app;
		this.message = message;
		this.connected = false;
	}
	
	public void run() {
		try (
				Socket socket = new Socket(hostName, portNumber);
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
		) {
			connected = true;
			while (true) {
				synchronized (message) {
					message.wait();
					System.out.println("I was waiting for it!");
					out.writeObject(message.getText());
				}
			}
		} catch (UnknownHostException uhe) {
			System.err.println("Don't know about host " + hostName);
		} catch (IOException ioe) {
			System.err.println("Couldn't get I/O for the connection to " + hostName);
		} catch (InterruptedException inte) {
			inte.printStackTrace();
		} finally {
			connected = false;
		}
	}
}
