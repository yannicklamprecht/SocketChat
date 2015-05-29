package server.events;

import server.events.callables.SCEvent;

/**
 * Created by yannick on 08.05.2015.
 */
public interface IEventBroadcaster {
    void callEvent(SCEvent event);
    void registerListener(IListener listener);
    void unregisterListener(IListener listener);
}
