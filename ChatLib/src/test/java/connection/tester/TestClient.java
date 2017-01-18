package de.thm.iem.ylmp88.socketconnection.test.tester;

import de.thm.iem.ylmp88.socketconnection.test.cs.Client;

/**
 * Created by yannick on 19.06.2015.
 */
public class TestClient {

    public static void main(String [] args){


        Client client = new Client("localHost",25565);

        client.start();

        client.registerPaketDelivery(paket -> {
            System.out.println(paket.getClass());

            if (paket instanceof TestPaket){
                System.out.println(((TestPaket)paket).getMessage());
            }

        });

        while (true){
            client.sendPaket(new TestPaket("TEst"));
        }

    }
}
