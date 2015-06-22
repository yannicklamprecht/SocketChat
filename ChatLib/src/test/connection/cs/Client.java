package test.connection.cs;


import de.thm.iem.ylmp88.chatlib.connection.Packet;
import de.thm.iem.ylmp88.chatlib.connection.PacketConnection;
import de.thm.iem.ylmp88.chatlib.connection.PacketDelivery;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by yannick on 19.06.2015.
 */
public class Client extends Thread {


    private PacketConnection connection;
    private Set<PacketDelivery> paketDeliveries = new HashSet<>();


    public Client(String host, int port) {

        try {
            connection = new PacketConnection(new Socket(host, port));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Client(Socket socket){
        try {
            connection = new PacketConnection(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void run() {

        while (connection!= null && !connection.isClosed()) {

            try {
                Packet paket = connection.readPacket();
                paketDeliveries.parallelStream().forEach(d -> d.deliverPacket(paket));
            } catch (SocketException e) {
                try {
                    connection.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public void registerPaketDelivery(PacketDelivery paketDelivery) {
        synchronized (paketDeliveries) {
            paketDeliveries.add(paketDelivery);
        }
    }

    public void sendPaket(Packet paket){
        try {
            connection.writePacket(paket);
        } catch (SocketException e) {
            try {
                connection.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isClosed(){
        return connection.isClosed();
    }

}
