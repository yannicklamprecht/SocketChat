package test.connection.tester;


import de.thm.iem.ylmp88.chatlib.connection.Packet;

/**
 * Created by yannick on 19.06.2015.
 */
public class TestPaket implements Packet {

    private String message;
    private long time;


    public TestPaket(String message){
        this.time= System.currentTimeMillis();
        this.message=message;
    }


    public String getMessage(){
        return message;
    }

    public long getTime(){
        return time;
    }



}
