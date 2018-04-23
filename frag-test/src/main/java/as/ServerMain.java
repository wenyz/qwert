package as;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class ServerMain {

    public static void main(String[] args) {

        SocketAddress masterAddress = new InetSocketAddress(11111);
        SocketAddress slave01Address = new InetSocketAddress(11112);
        SocketAddress slave02Address = new InetSocketAddress(11113);


//
//        Server slave01 = new SocketServer("slave01",false,slave01Address);
//        slave01.start();
//
//        Server slave02 = new SocketServer("slave02",false,slave02Address);
//        slave02.start();
//
//        SocketServer master001 = new SocketServer("master001",true,masterAddress);
//        master001.addSlave(slave01Address);
//        master001.addSlave(slave02Address);
//        master001.start();
//        try {
//            master001.startSyn();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
