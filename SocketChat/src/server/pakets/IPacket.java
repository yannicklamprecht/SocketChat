package server.pakets;

import java.io.Serializable;

/**
 * Created by yannick on 07.05.2015.
 */
public interface IPacket extends Serializable{

    Class<? extends IPacket> getType();

}
