package connection.test;

import java.io.IOException;

/**
 * Created by yannick on 18.06.2015.
 */
public class Start {

    public static void main(String [] args){


        Server server = new Server(25565);

        try {
            Client client = new NamedClient("Test");

            client.writeLine("Alles geht gut!");

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
