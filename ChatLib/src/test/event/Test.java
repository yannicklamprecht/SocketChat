package test.event;

import de.thm.iem.ylmp88.chatlib.eventsystem.EventSystemManager;

/**
 * Created by yannick on 02.06.2015.
 */
public class Test {


    public static void main(String[] args){

        EventSystemManager.manager.registerSubscriber(TestListener.class);

        EventSystemManager.manager.notifySubscriber(new NonSenseEvent("ACDC goes away"));
    }
}
