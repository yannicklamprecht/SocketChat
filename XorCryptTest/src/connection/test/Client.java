package connection.test;

import connection.pakets.PaketMessage;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by yannick on 18.06.2015.
 */
public class Client {

    private ObjectConnection connection;
    private Socket socket;


    public Client(Socket socket) {

        try {
            this.socket = socket;
            connection = new ObjectConnection(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void writeLine(String message) {
        connection.sendPaket(new PaketMessage(message));
    }




    public void addPaketAdapter(PaketAdapter adapter){
        connection.addPaketAdapter(adapter);
    }

}
