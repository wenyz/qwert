package as.internal;

import as.server.SocketServer;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
public class ServerDataDeliver implements Runnable {

    private Object lifecycle = new Object();
    private boolean running = false;
    private Future future;
    private ExecutorService executorService;
    private OutputStream os;

    private SocketServer socketServer;

    public ServerDataDeliver(ExecutorService executorService, OutputStream os, SocketServer socketServer) {
        this.executorService = executorService;
        this.os = os;
        this.socketServer = socketServer;
    }

    public void start() {

        synchronized (lifecycle) {
            if (!running) {
                running = true;
                future = executorService.submit(this);
            }
        }

    }

    public void stop() {

        synchronized (lifecycle) {
            if (future != null) {
                future.cancel(true);
                running = false;
            }
        }
    }

    public void run() {
        while (running && (os != null)) {

            try {
                //UserData userData = socketServer.pop();
                ObjectOutputStream oos = new ObjectOutputStream(os);
                // oos.writeObject(userData);
                oos.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
