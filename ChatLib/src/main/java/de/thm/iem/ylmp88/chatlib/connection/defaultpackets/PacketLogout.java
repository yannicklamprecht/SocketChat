package de.thm.iem.ylmp88.chatlib.connection.defaultpackets;

import de.thm.iem.ylmp88.chatlib.connection.Packet;

import java.net.InetAddress;

/**
 * Created by yannicklamprecht on 22.06.15.
 */
public class PacketLogout implements Packet {
    private InetAddress address;


    public PacketLogout(InetAddress address){
        this.address=address;
    }

    public InetAddress getAddress() {
        return address;
    }
}
