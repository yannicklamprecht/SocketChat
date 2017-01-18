package server.pakets;

import server.client.ICLient;
import server.pakets.connection.IConnectionManager;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by yannick on 07.05.2015.
 */
public class PacketInfoToCLient implements IPacket {

    private Set<? extends ICLient> clients;
    private IConnectionManager manager;

    public PacketInfoToCLient(IConnectionManager manager, Set<? extends ICLient> clients) {

        this.manager = manager;
        this.clients = clients;
    }


    @Override
    public Class<? extends IPacket> getType() {
        return PacketInfoToCLient.class;
    }

    @Override
    public IConnectionManager getConnectionManager() {
        return manager;
    }


    public Set<String> getUsernames() {

        Set<String> usernames = new HashSet<>();

        clients.forEach(c -> usernames.add(c.getName()));

        return usernames;
    }


}
