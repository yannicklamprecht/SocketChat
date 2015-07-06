package test.connection.tester;


import de.thm.iem.ylmp88.chatlib.connection.Packet;
import de.thm.iem.ylmp88.chatlib.connection.PacketDelivery;
import test.connection.cs.Client;

/**
 * Created by yannick on 19.06.2015.
 */
public class TestClient {

    public static void main(String [] args){


        Client client = new Client("localHost",25565);

        client.start();

        client.registerPaketDelivery(new PacketDelivery<TestPaket>() {
            @Override
            public void deliverPacket(TestPaket paket) {

                System.out.println(paket.getMessage());

            }
        });

        while (true){
            client.sendPaket(new TestPaket("TEst"));
        }

    }
}
