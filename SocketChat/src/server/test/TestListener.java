package server.test;

import server.events.EventHandler;
import server.events.IListener;

/**
 * Created by yannick on 30.05.2015.
 */
public class TestListener implements IListener {


    @EventHandler
    public void onInit(InitEvent e){

        System.out.println(e.getLol());
    }
}
