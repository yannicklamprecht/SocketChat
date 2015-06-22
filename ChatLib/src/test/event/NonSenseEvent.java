package test;

import de.thm.iem.ylmp88.chatlib.eventsystem.Event;

/**
 * Created by yannick on 02.06.2015.
 */
public class NonSenseEvent extends Event {

    private String test;

    public NonSenseEvent(String test){
        this.test=test;
    }

    public void doSomething(){

        System.out.print(test);
        System.out.print(test.length());
        System.out.print(test.toLowerCase());
    }


}
