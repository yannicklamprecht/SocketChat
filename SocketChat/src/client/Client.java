/**
 * Client.java
 * <p>
 * Created on , 16:46:43 by @author Yannick Lamprecht
 * <p>
 * SocketChat Copyright (C) 08.07.2014  Yannick Lamprecht
 * This program comes with ABSOLUTELY NO WARRANTY;
 * This is free software, and you are welcome to redistribute it
 * under certain conditions;
 */
package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author yannicklamprecht
 *
 */
public class Client extends Thread {

    private String name;
    private Socket connection;
    private BufferedReader reader;
    private BufferedReader lineReader;
    private PrintStream writer;

    public Client(String ip, int port, String uName) {
        InetSocketAddress soc = new InetSocketAddress(ip, port);
        this.lineReader = new BufferedReader(new InputStreamReader(System.in));
        this.name = uName;

        try {
            this.connection = new Socket(soc.getHostName(), soc.getPort());
            this.reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            this.writer = new PrintStream(connection.getOutputStream());
            this.start();
            sendMessage();

        } catch (IOException e) {
            System.out
                    .println("No connection established, server could be offline");
        }

    }

    public void sendMessage() {
        String line;

        try {

            line = lineReader.readLine();
            do {
                if (line == null || line.isEmpty()) {
                    System.out
                            .println("Type something and send it with enter.");
                } else {
                    writer.println(this.name + " > " + line);
                }
                line = lineReader.readLine();
            } while (!line.equalsIgnoreCase("/quit"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("See U");
        try {
            closeCLient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeCLient() throws IOException {

        this.connection.close();

        System.exit(0);

    }

    @Override
    public void run() {

        String s = null;
        while (true) {

            try {
                if ((s = reader.readLine()) != null) {
                    System.out.println(s);
                }
            } catch (IOException e) {
            }
        }
    }


    public static void main(String... args) {
        new Client("localhost", 25565, "ysl2");

    }

}
