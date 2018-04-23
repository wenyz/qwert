package as;

import as.client.SocketClient;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class ClientMain {

    public static void main(String[] args) {

        SocketAddress client001Address = new InetSocketAddress("localhost", 11111);

        SocketClient client001 = new SocketClient("client001", client001Address);
        client001.start();

    }
}
