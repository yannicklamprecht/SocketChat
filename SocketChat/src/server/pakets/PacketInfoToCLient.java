package server.pakets;

import server.client.ICLient;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by yannick on 07.05.2015.
 */
public class PacketInfoToCLient implements IPacket {
    @Override
    public Class<? extends IPacket> getType() {
        return PacketInfoToCLient.class;
    }

    private Set<? extends ICLient> clients;

    public PacketInfoToCLient(Set<? extends ICLient> clients) {
        this.clients = clients;
    }


    public Set<String> getUsernames() {

        Set<String> usernames = new HashSet<>();

        clients.forEach(c -> usernames.add(c.getName()));

        return usernames;
    }


}
