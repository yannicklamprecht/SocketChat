package connection.test;

import connection.pakets.PaketMessage;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by yannick on 18.06.2015.
 */
public class NamedClient extends Client {

    private String name;

    public NamedClient(String name) throws IOException {
        super(new Socket("127.0.0.1", 25565));
        this.name = name;
        this.addPaketAdapter(new MessageAdapter());
    }

    private class MessageAdapter implements PaketAdapter {
        @Override
        public void handlePaket(Paket paket) {

            System.out.println(paket.getClass());
            if (paket instanceof PaketMessage) {
                PaketMessage messagePaket = (PaketMessage) paket;

                System.out.println(messagePaket.getMessage());
            }
        }
    }
}
