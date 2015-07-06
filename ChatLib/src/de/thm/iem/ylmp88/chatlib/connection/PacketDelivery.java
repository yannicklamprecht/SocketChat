package de.thm.iem.ylmp88.chatlib.connection;

/**
 * Created by yannick on 19.06.2015.
 */
public interface PacketDelivery<T> {
    void deliverPacket(T packet);
}
