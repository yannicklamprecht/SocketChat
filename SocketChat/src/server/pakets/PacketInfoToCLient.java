package server.pakets;

import server.client.ICLient;

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

    public PacketInfoToCLient(Set<? extends ICLient> clients){
        this.clients=clients;
    }


    //TODO implement clientlist to Stringnamelist
    public Set<String> getUsernames(){
        return clients.forEach(c -> c.getName());
    }











}
