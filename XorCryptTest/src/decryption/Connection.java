package decryption;

import javax.crypto.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Created by yannick on 07.06.2015.
 */
public class Connection {

    private SecretKey key;
    private byte[] salt;

    public ObjectOutputStream wrapper(Socket socket) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {

        OutputStream ostream = socket.getOutputStream();

        OutputStream base64OsStream =Base64.getEncoder().wrap(ostream);


        KeyGenerator kg= KeyGenerator.getInstance("AES");
        kg.init(56,new SecureRandom());
        Key key=kg.generateKey();
        Cipher c=Cipher.getInstance("AES");
        c.init(Cipher.DECRYPT_MODE,key);

        CipherOutputStream cipherOutputStream = new CipherOutputStream(base64OsStream,c);


        return new ObjectOutputStream(cipherOutputStream);

    }

    public InputStream addFilter(InputStream in,String path,String mimeType,String encoding) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        Cipher dcipher;
        KeyGenerator kg= KeyGenerator.getInstance("AES");
        kg.init(56,new SecureRandom());
        Key key=kg.generateKey();
        Cipher c=Cipher.getInstance("AES");
        c.init(Cipher.ENCRYPT_MODE,key);
        CipherInputStream cis=new CipherInputStream(in,c);
        return  cis;
    }

    private SecretKey getSecretKey() {
        return key;
    }

}
