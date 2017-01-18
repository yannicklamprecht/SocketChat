/**
 * Server.java
 * 
 * Created on , 17:13:11 by @author Yannick Lamprecht
 *
 * SocketChat Copyright (C) 08.07.2014  Yannick Lamprecht
 * This program comes with ABSOLUTELY NO WARRANTY;
 * This is free software, and you are welcome to redistribute it
 * under certain conditions;
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yannicklamprecht
 *
 */
public class Server extends Thread {

	private ServerSocket server;
	private List<Clients> clients;
	private int max = 5;

	public Server(int port) {

		try {
			clients = new ArrayList<Clients>();
			this.server = new ServerSocket(port);
			System.out.println("Startet");
			start();
			
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					
					for(Clients cl: clients){
						if(cl.isCLosed()){
							clients.remove(cl);
						}
					}
					
					try {
						Thread.sleep(1000L* 60);
					} catch (InterruptedException e) {
					}
				}
			}).start();
			

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {

		for (int i = 0; i < max; i++) {
			try {
				Socket s = this.server.accept();
				
				System.out.println(s.getInetAddress());
				
				
				Clients c = new Clients(s,clients);
				clients.add(c);
				System.out.println("Client: "+ c.toString()+" hinzugefügt");
				c.sendMessageToCLient("Du hast dich angemeldet");
				c.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
	
	public static void main(String args[]){
		new Server(25565);
		
	}
	
}
