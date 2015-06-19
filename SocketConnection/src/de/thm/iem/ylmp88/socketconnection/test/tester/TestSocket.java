package de.thm.iem.ylmp88.socketconnection.test.tester;

import de.thm.iem.ylmp88.socketconnection.test.cs.Server;

/**
 * Created by yannick on 19.06.2015.
 */
public class TestSocket {


    public static void main(String args[]){

        Server server = new Server(25565,2);
        server.start();

    }
}
