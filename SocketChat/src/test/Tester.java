import server.events.EventBroadcaster;
import server.events.Handler;

/**
 * Created by yannick on 29.05.2015.
 */
public class Tester {

    public static void main(String [] args){


        Handler.registerListener(TestListener.class);


        System.out.print(Handler.toStringS());

        EventBroadcaster.callEvent(new InitEvent("Test"));



    }
}
