import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by yannick on 02.06.2015.
 */
public class Client {

    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private boolean isConnected = false;


    public Client() {
        try {
        socket = new Socket("localHost", 4445);
        System.out.println("Connected");
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (SocketException se) {
            se.printStackTrace();
            // System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void communicate() {

        while (!isConnected) {


                Student student = new Student(1, "Bijoy");
                System.out.println("Object to be written = " + student);
            try {
                outputStream.writeObject(student);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    public static void main(String[] args) {
        Client client = new Client();
        client.communicate();
    }

}
