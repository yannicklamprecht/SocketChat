package de.thm.iem.ylmp88.socketconnection.test.cs;

import de.thm.iem.ylmp88.socketconnection.Packet;
import de.thm.iem.ylmp88.socketconnection.PaketConnection;
import de.thm.iem.ylmp88.socketconnection.PaketDelivery;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by yannick on 19.06.2015.
 */
public class Client extends Thread {


    private PaketConnection connection;
    private Set<PaketDelivery> paketDeliveries = new HashSet<>();


    public Client(String host, int port) {

        try {
            connection = new PaketConnection(new Socket(host, port));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Client(Socket socket){
        try {
            connection = new PaketConnection(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void run() {

        while (connection!= null && !connection.isClosed()) {

            try {
                Packet paket = connection.readPaket();
                paketDeliveries.parallelStream().forEach(d -> d.deliverPaket(paket));
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

    public void registerPaketDelivery(PaketDelivery paketDelivery) {
        synchronized (paketDeliveries) {
            paketDeliveries.add(paketDelivery);
        }
    }

    public void sendPaket(Packet paket){
        try {
            connection.writePaket(paket);
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
