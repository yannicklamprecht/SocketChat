package connection.pakets;

import connection.test.Paket;

/**
 * Created by yannick on 18.06.2015.
 */
public class PaketMessage extends Paket {

    private String message;

    public PaketMessage(String message){
        this.message=message;
    }

    public String getMessage(){
        return message;
    }

}
