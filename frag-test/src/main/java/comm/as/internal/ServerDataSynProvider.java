package comm.as.internal;

import comm.as.ser.SocketServer;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ServerDataSynProvider implements Runnable {

    private Object lifecycle = new Object();
    private boolean running = false;
    private Future future;
    private ExecutorService executorService;

    private InputStream in;
    private SocketServer socketServer;

    public ServerDataSynProvider(ExecutorService executorService, InputStream in, SocketServer socketServer) {
        this.executorService = executorService;
        this.in = in;
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

        while (running && (in != null)) {

            try {
                ObjectInputStream ois = new ObjectInputStream(in);
                UserData userData = (UserData) ois.readObject();
                // socketServer.push(userData);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
