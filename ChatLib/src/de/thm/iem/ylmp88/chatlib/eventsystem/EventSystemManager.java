package de.thm.iem.ylmp88.chatlib.eventsystem;

/**
 * Created by yannick on 02.06.2015.
 */
public class EventSystemManager {

    public final static EventSystemManager manager = new EventSystemManager();

    private SubscriberRegistry registry = new SubscriberRegistry();
    private Notifier notifier = new Notifier(registry);


    public Notifier getNotifier(){
        return notifier;
    }

    public void registerSubscriber( Class<? extends Subscriber> subscriber){
        registry.subscribe(subscriber);
    }

    public void notifySubscriber(Event event){
        notifier.notify(event);
    }


}
