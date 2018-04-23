package as.internal;


import as.server.SocketServer;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ServerDataReceiver implements Runnable {

    //  private  Map<String, UserData> data;
    private InputStream in;
    private ExecutorService executorService;
    private SocketServer socketServer;
    private ServerState serverState;

    private Object lifecycle = new Object();
    private boolean running = false;
    private Future future;


    public ServerDataReceiver(ServerState serverState, InputStream in, ExecutorService executorService, SocketServer socketServer) {
        this.serverState = serverState;
        this.in = in;
        this.executorService = executorService;
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
                ObjectInputStream obj = new ObjectInputStream(in);
                UserData userData = (UserData) obj.readObject();

//                data.put(userData.getUserId(),userData);
//                socketServer.push(userData);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
