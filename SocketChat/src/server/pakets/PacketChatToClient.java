package server.pakets;

/**
 * Created by yannick on 07.05.2015.
 */
public class PacketChatToClient implements IPacket {
    @Override
    public Class<? extends IPacket> getType() {
        return PacketChatToClient.class;
    }


    private String message;


    public PacketChatToClient(String message){
        this.message=message;
    }


    public String getMessage(){
        return message;
    }
}
