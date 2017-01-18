package de.thm.iem.ylmp88.connection.cs;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by yannick on 19.06.2015.
 */
public class Server extends Thread {


    private Set<SClient> sClients = new HashSet<>();
    private ServerSocket serverSocket;
    private int maxClientConnections;
    private int currentClientCount = 0;

    public Server(int port, int maxClientConnections) {
        this.maxClientConnections = maxClientConnections;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {

        while (currentClientCount < maxClientConnections) {

            try {
                Socket socket = serverSocket.accept();

                SClient sClient = new SClient(socket);
                sClient.registerPaketDelivery(paket -> {
                    synchronized (sClient) {
                        if (!sClient.isClosed())
                            sClient.sendPaket(paket);
                    }
                });

                //TODO Write a unregister Class for clients. Define a paket for unregistering
                sClients.add(sClient);
                sClient.start();
                System.out.println("ClientAdded");
                currentClientCount++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class SClient extends Client {
        public SClient(Socket socket) {
            super(socket);
        }
    }

}
