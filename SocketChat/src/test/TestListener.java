import server.events.EventHandler;

/**
 * Created by yannick on 30.05.2015.
 */
public class TestListener{


    @EventHandler
    public void onInit(InitEvent e){
        System.out.println(e.getLol());
    }
}
