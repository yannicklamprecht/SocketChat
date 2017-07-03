/**
 * Clients.java
 * 
 * Created on , 17:16:37 by @author Yannick Lamprecht
 *
 * SocketChat Copyright (C) 08.07.2014  Yannick Lamprecht
 * This program comes with ABSOLUTELY NO WARRANTY;
 * This is free software, and you are welcome to redistribute it
 * under certain conditions;
 */
package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;

/**
 * @author yannicklamprecht
 *
 */
public class Clients extends Thread {

	private List<Clients> list;
	private BufferedReader reader;
	private PrintStream writer;
	private Socket soc;

	public Clients(Socket connection, List<Clients> list) {
		this.list = list;
		this.soc = connection;
		try {
			this.reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			this.writer = new PrintStream(connection.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void sendMessageToCLient(String message) {
		writer.println(message);
	}

	public void publishMessage(String message) {
		for (Clients c : list) {
			c.sendMessageToCLient(message);
		}
	}
	
	public boolean isCLosed(){
		return this.soc.isConnected();
	}

	@Override
	public void run() {

		String message = null;

		try {
			message = reader.readLine();

			while (message != null) {
				
				if(message.equalsIgnoreCase("/quit")){
					this.soc.close();
					this.list.remove(this);
					
					break;
					
				}else{
				publishMessage(message+ " ["+list.size()+"]");
				message = reader.readLine();
				}
			}
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}