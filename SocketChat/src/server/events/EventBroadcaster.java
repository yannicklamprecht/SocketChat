package server.events;

import server.events.callables.Event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by yannick on 08.05.2015.
 */
public class EventBroadcaster {


    public static void callEvent(Event event) {


        //TODO check invocation of this method
        for (Class<?> listener : Handler.getHandlers()) {

            for (Method m : listener.getClass().getMethods()) {

                EventHandler ev = m.getAnnotation(EventHandler.class);


                if (ev != null) {

                    Class[] methodP = m.getParameterTypes();

                    if (methodP.length < 1) continue;

                    if (!event.getClass().getSimpleName().equals(methodP[0].getSimpleName())) continue;

                    try {
                        m.invoke(listener.newInstance(), event);
                    } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                        e.printStackTrace();
                    }
                }


            }
        }

    }


}
