package de.thm.iem.ylmp88.chatlib.connection.defaultpackets;

import de.thm.iem.ylmp88.chatlib.connection.Packet;

import java.net.InetAddress;

/**
 * Created by yannicklamprecht on 22.06.15.
 */
public class PacketLogin implements Packet {

    private InetAddress address;
    private String chatName;


    public PacketLogin(InetAddress address, String chatName){
        this.address=address;
        this.chatName=chatName;

    }

    public String getChatName(){
        return chatName;
    }

    public InetAddress getAddress() {
        return address;
    }
}
