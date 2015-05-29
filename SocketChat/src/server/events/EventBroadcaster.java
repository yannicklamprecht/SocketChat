package server.events;

import server.events.callables.SCEvent;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yannick on 08.05.2015.
 */
public class EventBroadcaster implements IEventBroadcaster{


    List<Class<? extends IListener>> listeners = new ArrayList<>();


    public synchronized void registerListener(IListener listener){
        listeners.add(listener.getClass());
    }

    public synchronized void unregisterListener(IListener listener){
        listeners.remove(listener.getClass());
    }

    public void callEvent(SCEvent event){


        //TODO check invocation of this method
        for(Class<? extends IListener> listener : listeners){

            for( Method m : listener.getClass().getMethods()){

                if(m.getAnnotation(EventHandler.class) != null){

                    if(m.getParameterCount()!=1)continue;

                    if(!event.getClass().getSimpleName().equals(m.getParameterTypes()[0].getSimpleName()))continue;

                    try {
                        m.invoke(listener.newInstance(),event);
                    } catch (IllegalAccessException |InvocationTargetException |InstantiationException e) {
                        e.printStackTrace();
                    }
                }


            }
        }

    }




}
