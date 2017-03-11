package main;

public class Peer {
	private Client client;
	private Server server;
	
	public Peer(Client client, Server server) {
		this.client = client;
		this.server = server;
	}
	
	public Client getClient() {
		return this.client;
	}
	
	public Server getServer() {
		return this.server;
	}
}
