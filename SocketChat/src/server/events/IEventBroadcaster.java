package server.events;

import server.events.callables.SCEvent;

/**
 * Created by yannick on 08.05.2015.
 */
public interface IEventBroadcaster {
    static void callEvent(SCEvent event){};
    static void registerListener(IListener listener){};
    static void unregisterListener(IListener listener){};
}
