package server.test;

import server.events.callables.SCEvent;

/**
 * Created by yannick on 30.05.2015.
 */
public class InitEvent extends SCEvent {

    private String lol;

    public String getLol() {
        return lol;
    }

    public InitEvent(String lol){
        this.lol=lol;
    }
}
