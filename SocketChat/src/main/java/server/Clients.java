/**
 * Clients.java
 * <p>
 * Created on , 17:16:37 by @author Yannick Lamprecht
 * <p>
 * SocketChat Copyright (C) 08.07.2014  Yannick Lamprecht
 * This program comes with ABSOLUTELY NO WARRANTY;
 * This is free software, and you are welcome to redistribute it
 * under certain conditions;
 */
package server;

import server.pakets.IPacket;
import server.pakets.PacketChatToClient;
import server.pakets.connection.ConnectionManager;
import server.pakets.connection.IConnectionManager;
import server.pakets.connection.PacketHandler;

import java.io.*;
import java.net.Socket;
import java.util.List;

/**
 * @author yannicklamprecht
 */
public class Clients extends Thread {

    private List<Clients> list;

    private IConnectionManager connectionManager;

    public Clients(Socket connection, List<Clients> list) {
        this.list = list;
        this.connectionManager = new ConnectionManager(connection);
    }

    public void sendMessageToCLient(String message) throws IOException {
        connectionManager.sendMessage(message);
    }

    public void publishMessage(String message) {
        for (Clients c : list) {
            try {
                c.sendMessageToCLient(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isCLosed() {
        return this.connectionManager.isCLosed();
    }

    @Override
    public void run() {

        PacketHandler.handler.deliverPacket(connectionManager.readPacket());

                /*
                if (message.equalsIgnoreCase("/quit")) {
                    this.soc.close();
                    this.list.remove(this);

                    break;

                } else {
                    publishMessage(message + " [" + list.size() + "]");
                    message = reader.readLine();
                }
            */
    }


}

