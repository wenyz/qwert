package file.nio;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class LacClient {


    public static void main(String[] args) throws Exception {

//        int threadCount = 5;
//
//        Thread[] threads = new Thread[threadCount];
//
//        for (int i = 0; i < threadCount; i++) {
//
//            threads[i] = new Thread(new multiSocket(LacLoader.getSocketList(), "thread::" + i));
//            threads[i].start();
//
//        }
        int i = 0;

        Socket socket = null;
        BufferedOutputStream bos = null;
        final BufferedInputStream bis;
        try {
            SocketAddress address = new InetSocketAddress("localhost", 10032);
            socket = new Socket();
            socket.connect(address);
            bos = new BufferedOutputStream(socket.getOutputStream());
            bis = new BufferedInputStream(socket.getInputStream());

            new Thread(new Runnable() {
                public void run() {
                    while (true) {

                        try {
                            byte[] abc = new byte[bis.read()];
                            bis.read(abc);
                            System.out.println("new Thread:" + new String(abc));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }).start();

            while (true) {

                String out = "TTTTTTTT" + i++;
                bos.write(out.getBytes());
                System.out.println(out);
//                bos.flush();

            }
//                bos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("close");
            socket.close();
//                if (bos != null) {
//                    bos.close();
//                }
//                if (socket != null) {
//                    socket.close();
//                    socket=null;
//                }

        }


    }

}
