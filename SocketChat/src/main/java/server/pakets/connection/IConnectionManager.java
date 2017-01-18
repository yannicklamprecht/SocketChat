package server.pakets.connection;

import server.pakets.IPacket;

import java.io.IOException;

/**
 * Created by yannick on 08.05.2015.
 */
public interface IConnectionManager {
    //TODO find more usefull/functional methods

    void sendPacket(IPacket packet) throws IOException;
    IPacket readPacket();

    void sendMessage(String message) throws IOException;

    boolean close();

    boolean isCLosed();


}
