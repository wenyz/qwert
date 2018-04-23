package comm.as.internal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ServerDataSynConsumer implements Runnable {

    private Object lifecycle = new Object();
    private boolean running = false;
    private Future future;

    private ExecutorService executorService;

    private ServerState serverState;


    public ServerDataSynConsumer(ExecutorService executorService, ServerState serverState) {
        this.executorService = executorService;
        this.serverState = serverState;
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

        while (true) {
            try {
                UserData userData = serverState.pop();
                serverState.addData(userData);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
