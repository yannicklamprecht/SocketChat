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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * @author yannicklamprecht
 */
public class Clients extends Thread {

    private Server server;
    private BufferedReader reader;
    private PrintStream writer;
    private Socket soc;

    public Clients(Socket connection, Server server) {
        this.server = server;
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


    public boolean isCLosed() {
        return this.soc.isConnected();
    }

    public void close() throws IOException {
        this.soc.close();
    }

    @Override
    public void run() {

        String message;

        try {
            message = reader.readLine();

            while (message != null) {

                if (message.equalsIgnoreCase("/quit")) {
                    this.server.disconnect(this);
                    break;

                } else {
                    this.server.publishMessage(message);
                    message = reader.readLine();
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
