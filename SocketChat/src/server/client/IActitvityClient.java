package server.client;

import server.pakets.IPacket;
import server.pakets.connection.IConnectionManager;

import java.net.SocketAddress;
import java.util.UUID;

/**
 * Created by yannick on 07.05.2015.
 */
public interface IActitvityClient extends ICLient {

    UUID getID();
    void setName(String name);
    SocketAddress getAdress();
    IConnectionManager getConnection();
}
