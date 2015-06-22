package de.thm.iem.ylmp88.chatlib.connection;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by yannick on 19.06.2015.
 */
public class PaketConnection implements Closeable {


    private Socket socket;

    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;


    public PaketConnection(Socket socket) throws IOException {
        this.socket = socket;
        this.outputStream = new ObjectOutputStream(socket.getOutputStream());
        this.inputStream = new ObjectInputStream(socket.getInputStream());
    }


    public void writePaket(Packet paket) throws IOException {
        outputStream.writeObject(paket);
        outputStream.flush();
    }

    public Packet readPaket() throws IOException, ClassNotFoundException {

        Object object = inputStream.readObject();

        if (object instanceof Packet) {
            return (Packet) object;
        }

        return null;
    }

    public boolean isClosed() {
        return socket.isClosed();
    }


    @Override
    public void close() throws IOException {
        socket.close();
    }
}
