package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			System.err.println("Usage: java Client <hostname> <portnumber>");
			System.exit(1);
		}
		String hostName = args[0];
		int portNumber = Integer.parseInt(args[1]);
		try (
				Socket socket = new Socket(hostName, portNumber);
				PrintWriter out = new PrintWriter(
						socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(
						new InputStreamReader(socket.getInputStream()));
				BufferedReader stdIn = new BufferedReader(
						new InputStreamReader(System.in));
		) {
			String userInput;
			while ((userInput = stdIn.readLine()) != null) {
				out.println(userInput);
				System.out.println("echo : " + in.readLine());
			}
			
		} catch (UnknownHostException uhe) {
			System.err.println("Don't know about host " + hostName);
			System.exit(1);
		} catch (IOException ioe) {
			System.err.println("Couldn't get I/O for the connection to " + hostName);
			System.exit(1);
		}
	}
}