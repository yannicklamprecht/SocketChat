package connection.test;

import connection.pakets.PaketMessage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by yannick on 18.06.2015.
 */
public class Server extends Thread {

    private ServerSocket serverSocket;

    private Set<Client> clients = new HashSet<>();

    private int maxClientSize = 3;

    private boolean closed = false;

    public Server(int port) {

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        start();
    }


    @Override
    public void run() {

        while (!closed && clients.size() < maxClientSize) {

            try {
                Socket clientSocket = serverSocket.accept();

                Client client = new Client(clientSocket);

                client.addPaketAdapter(paket -> {
                    System.out.println(paket.getClass());
                    if (paket instanceof PaketMessage) {
                        client.writeLine(((PaketMessage) paket).getMessage());
                    }
                });

                clients.add(client);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
