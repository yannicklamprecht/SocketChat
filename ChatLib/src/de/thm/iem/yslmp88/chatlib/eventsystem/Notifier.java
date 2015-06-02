package de.thm.iem.yslmp88.chatlib.eventsystem;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by yannick on 02.06.2015.
 */
public class Notifier {

    private SubscriberRegistry registry ;

    public  Notifier(SubscriberRegistry registry){
        this.registry=registry;
    }

    public void notify(Event event){

        List<Class<?>> subscribers = registry.getSubscribers();


        for (Class<?> subscriber: subscribers){

            Method[] methods = subscriber.getMethods();


            for (Method m : methods){

                if(m.getAnnotation(Subscribe.class)== null)continue;

                Class<?>[]  paClass = m.getParameterTypes();

                if (paClass.length<1)continue;

                try {
                    m.invoke(subscriber.newInstance(),event);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void  notifyAsync(Event event){
        new Thread( () -> { notify(event);}).start();
    }

}
