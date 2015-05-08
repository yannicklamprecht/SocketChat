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
import server.pakets.connection.PacketHandler;

import java.io.*;
import java.net.Socket;
import java.util.List;

/**
 * @author yannicklamprecht
 *
 */
public class Clients extends Thread {

    private List<Clients> list;

    private ObjectOutputStream out;
    private ObjectInputStream in;

    private Socket soc;

    public Clients(Socket connection, List<Clients> list) {
        this.list = list;
        this.soc = connection;
        try {
            this.in = new ObjectInputStream(
                    connection.getInputStream());
            this.out = new ObjectOutputStream(connection.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendMessageToCLient(String message) throws IOException {
        out.writeObject(new PacketChatToClient(message));
    }

    public void publishMessage(String message) {
        for (Clients c : list) {
            try {
                c.sendMessageToCLient(message);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public boolean isCLosed() {
        return this.soc.isConnected();
    }

    @Override
    public void run() {

        Object packet = null;

        try {
            packet = in.readObject();

            while (packet != null) {


                if(packet instanceof IPacket){
                    PacketHandler.handler.deliverPacket((IPacket)packet);
                }

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




        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
