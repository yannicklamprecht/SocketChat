package server.pakets.connection;

import server.pakets.IPacket;
import server.pakets.PacketChatToClient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by yannick on 08.05.2015.
 */
public class ConnectionManager implements IConnectionManager {

    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Socket socket;


    public ConnectionManager(Socket socket) {
        this.socket = socket;
        try {
            this.in = new ObjectInputStream(
                    socket.getInputStream());
            this.out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean close() {

        try {
            out.close();
            in.close();
            socket.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean isCLosed() {
        return socket.isClosed();
    }


    @Override
    public void sendPacket(IPacket packet) throws IOException {
        out.writeObject(packet);
        out.flush();
    }

    @Override
    public IPacket readPacket() {
        try {
            return (IPacket) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void sendMessage(String message) throws IOException {
        sendPacket(new PacketChatToClient(message));
    }
}
