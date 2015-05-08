package server.pakets.connection;

import server.pakets.IPacket;

/**
 * Created by yannick on 08.05.2015.
 */
public interface IConnectionManager {
    //TODO find more usefull/functional methods

    void sendPacket(IPacket packet);
    IPacket readPacket();

    void sendMessage(String message);

}
