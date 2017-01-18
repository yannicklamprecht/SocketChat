package server.pakets;

import server.pakets.connection.IConnectionManager;

/**
 * Created by yannick on 07.05.2015.
 */
public class PacketChatToClient implements IPacket {
    @Override
    public Class<? extends IPacket> getType() {
        return PacketChatToClient.class;
    }

    private String message;
    private IConnectionManager manager;

    @Override
    public IConnectionManager getConnectionManager() {
        return manager;
    }


    public PacketChatToClient(IConnectionManager manager,String message){
        this.manager=manager;
        this.message=message;
    }


    public String getMessage(){
        return message;
    }
}
