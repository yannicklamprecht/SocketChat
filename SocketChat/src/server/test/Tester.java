package server.test;

import server.events.EventBroadcaster;

/**
 * Created by yannick on 29.05.2015.
 */
public class Tester {

    public static void main(String [] args){


        EventBroadcaster.registerListener(new TestListener());



        EventBroadcaster.callEvent(new InitEvent("Test"));



    }
}
