package de.thm.iem.ylmp88.chatlib.connection.defaultpackets;

import de.thm.iem.ylmp88.chatlib.connection.Packet;
import de.thm.iem.ylmp88.chatlib.connection.PacketDelivery;
import de.thm.iem.ylmp88.chatlib.eventsystem.Event;
import de.thm.iem.ylmp88.chatlib.eventsystem.EventSystemManager;

/**
 * Created by yannicklamprecht on 06.07.15.
 */
public class EventPacketDelivery {

    public static final PacketDelivery<Packet> eventPacketDelivery = packet -> {
        if(packet instanceof Event)
            EventSystemManager.manager.notifySubscriber((Event)packet);

    };
}
