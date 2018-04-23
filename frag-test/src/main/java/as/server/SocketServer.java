package as.server;

import as.Server;
import as.internal.*;

import javax.net.SocketFactory;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SocketServer implements Server, Runnable {

    private static final String SERVER_VERSION = "0.1";
    private final List<SocketAddress> slaveAddress;
    private String serverName;
    private boolean isMaster = false;
    private String host;
    private int port;
    private SocketAddress serverAddress;
    private ServerSocket serverSocket;
    private Socket socket;

    //private ServerDataReceiver dataReceiver;
    private ServerDataSynConsumer synConsumer;
    private ServerDataSynProvider synProvider;

    private ServerState serverState;
    private ExecutorService executorService;

    private boolean running = false;
    private Future serverFuture;
    private Object lifecycle = new Object();

    public SocketServer(String serverName, boolean isMaster, String host, int port) {
        this.serverName = serverName;
        this.isMaster = isMaster;
        this.host = host;
        this.port = port;

        this.slaveAddress = new ArrayList<>();
        this.executorService = Executors.newCachedThreadPool();

        bindServer();

    }

    public void startSyn() throws IOException {

        for (SocketAddress address : slaveAddress) {

            Socket socket = new Socket();
            socket.connect(address);

            ServerDataDeliver deliver = new ServerDataDeliver(executorService, socket.getOutputStream(), this);
            deliver.start();
            System.out.println("synchronized data deliver has started " + address.toString());

        }

    }

    @Override
    public void start() {

        synchronized (lifecycle) {
            if (!running) {
                running = true;
                serverFuture = executorService.submit(this);
            }
        }
    }

    @Override
    public void stop() {

        synchronized (lifecycle) {

            if (serverFuture != null) {
                serverFuture.cancel(true);
                running = false;
            }
        }
    }


    @Override
    public void run() {

        while (running) {

            try {
                Socket socket = serverSocket.accept();
                System.out.println(socket.toString());

                if (socket.getInputStream() != null) {
                    System.out.println("==== a new socket(dataReceiver) has add in ");
                    ServerDataReceiver dataReceiver = new ServerDataReceiver(serverState, socket.getInputStream(), executorService, this);
                    dataReceiver.start();
                    continue;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    private void bindServer() {

        try {
            this.serverSocket = new ServerSocket();
            this.serverSocket.bind(serverAddress);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("server " + serverName + " has started !!!");

    }

    public void addSlave(SocketAddress address) {
        slaveAddress.add(address);
    }

    @Override
    public String getName() {
        return this.serverName;
    }

    private NetworkModule createNetworkModule() {

        return new TCPNetworkModule(SocketFactory.getDefault(), host, port);
    }

}
