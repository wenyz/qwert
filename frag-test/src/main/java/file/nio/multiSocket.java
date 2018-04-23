package file.nio;

import java.io.BufferedOutputStream;
import java.net.Socket;
import java.util.List;

class multiSocket implements Runnable {

    //ConcurrentMap<String,Socket> socketMap;
    List<Socket> socketList;
    String threadName;

    public multiSocket() {
    }

    public multiSocket(List<Socket> socketList, String threadName) {
        this.socketList = socketList;
        this.threadName = threadName;
    }

    public void run() {
        Socket socket = null;
        while (true) {
            // test point
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (socketList.size() > 0) {
                socket = socketList.get(0);
                socketList.remove(socket);
            } else {
                try {
                    Thread.currentThread().wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("return");
                return;
            }
            BufferedOutputStream ds = null;
            try {
                ds = new BufferedOutputStream(socket.getOutputStream());
                String out = threadName + "::::TTTTTTTTTT";
                ds.write(out.getBytes());
                ds.flush();
                System.out.println(out);
//                ds.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                socketList.add(socket);
            }
        }
    }
}
