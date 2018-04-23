package file.nio;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class LacServer {

    public static void main(String[] args) throws Exception {

        ServerSocket ss = new ServerSocket();
        ss.bind(new InetSocketAddress(10032));

        // while (true) {
        Socket socket = ss.accept();
        System.out.println("bbbbb");
        while (socket != null && socket.isConnected() && !socket.isClosed()
                && Thread.currentThread().isAlive()) {
            System.out.println("kkkkk");
            System.out.println(socket.isConnected());
            System.out.println(socket.isClosed());
            System.out.println("kkkkk");
            BufferedInputStream bis;


            while (socket.getInputStream() != null) {
                bis = new BufferedInputStream(socket.getInputStream());
                System.out.println("ooooo");

                BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
                bos.write("ggggggggggggggggggggggggggggggggggggg".getBytes());
                bos.flush();

                try {
                    byte[] abc = new byte[bis.read()];
                    bis.read(abc);
                    System.out.println(new String(abc));
                } catch (Exception e) {
                    //e.printStackTrace();
                    socket.close();
                    break;
                } finally {
//                bis.close();
                }
            }

            //   }

        }


    }
}
