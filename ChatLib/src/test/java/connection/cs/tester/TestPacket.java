package test.connection.cs.tester;

import de.thm.iem.ylmp88.socketconnection.Packet;

/**
 * Created by yannick on 19.06.2015.
 */
public class TestPacket implements Packet {

    private String message;
    private long time;


    public TestPacket(String message){
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
