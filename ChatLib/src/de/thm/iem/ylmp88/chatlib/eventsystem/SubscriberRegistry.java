package de.thm.iem.ylmp88.chatlib.eventsystem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yannick on 02.06.2015.
 */
public class SubscriberRegistry {

    private List<Class<?>> subscribers = new ArrayList<>();

    public void subscribe(Class<?> subscriber){
        subscribers.add(subscriber);
    }

    public List<Class<?>> getSubscribers(){
        return subscribers;
    }

}
