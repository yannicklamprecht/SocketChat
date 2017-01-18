package server.events;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yannick on 02.06.2015.
 */
public class Handler {


    private static List<Class> listeners = new ArrayList<>();


    public static void registerListener(Class<?> listener) {
        listeners.add(listener);
    }

    public static void unregisterListener(Class<?> listener) {
        listeners.remove(listener);
    }

    public static List<Class> getHandlers() {
        return listeners;
    }


    public static String toStringS(){

        StringBuilder builder = new StringBuilder();

        listeners.forEach(l -> builder.append(l.getClass().getSimpleName()+", "));


        return builder.toString();
    }

}
