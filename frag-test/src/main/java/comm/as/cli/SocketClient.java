package comm.as.cli;


import comm.as.Client;
import comm.as.internal.UserData;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SocketClient implements Client, Runnable {

    private Object lifecycle = new Object();
    private boolean running = false;
    private Future future;
    private ExecutorService executorService;
    private String clientName;
    private OutputStream out;

    public SocketClient(String clientName, SocketAddress serverAddress) {
        this.clientName = clientName;
        this.executorService = Executors.newSingleThreadExecutor();

        Socket socket = new Socket();
        try {
            socket.connect(serverAddress);
            this.out = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("client " + clientName + " has created !!!");
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
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            UserData userData = new UserData();
            userData.setUserId(UUID.randomUUID().toString());
            userData.setUserId(UUID.randomUUID().toString() + System.currentTimeMillis());

            try {
                ObjectOutputStream oos = new ObjectOutputStream(out);
                oos.writeObject(userData);
                oos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
