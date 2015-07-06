/**
 * Created by yannick on 02.06.2015.
 */

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {
    private ServerSocket serverSocket = null;
    private Socket socket = null;
    private ObjectInputStream inStream = null;

    public Server() {
        try {
            serverSocket = new ServerSocket(4445);
            socket = serverSocket.accept();
            System.out.println("Connected");
            inStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void communicate() {
        try {

            while (true){

                Student student = (Student) inStream.readObject();
                System.out.println("Object received = " + student);
                System.out.println("Object name = " + student.getName() + " id= " + student.getId());
            }

        } catch (SocketException se) {
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException cn) {
            cn.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Server server = new Server();
        server.communicate();
    }
}
