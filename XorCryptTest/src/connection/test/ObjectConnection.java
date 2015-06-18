package connection.test;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by yannick on 18.06.2015.
 */
public class ObjectConnection extends Thread implements Closeable {


    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private Set<PaketAdapter> paketAdapter = new HashSet<>();
    private boolean closed = false;

    private Socket socket;

    public ObjectConnection(Socket socket) throws IOException {
        this.socket = socket;

        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
        start();
    }


    public synchronized void addPaketAdapter(PaketAdapter adapter) {
        paketAdapter.add(adapter);
    }


    public void sendPaket(Paket paket) {
        try {
            outputStream.writeObject(paket);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {

        while (!closed) {
            try {
                if (inputStream.available() > 0) {
                    Object object = inputStream.readObject();
                    if (object instanceof Paket) {
                        Paket paket = (Paket) object;
                        paketAdapter.parallelStream().forEach( a -> {a.handlePaket(paket);});
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }


    }

    public void close() throws IOException {
        closed = true;
        inputStream.close();
        outputStream.close();
        socket.close();
    }

}
