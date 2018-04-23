package comm.as.internal;

import javax.net.SocketFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class TCPNetworkModule implements NetworkModule {

    private SocketFactory socketFactory;
    private Socket socket;

    private String host;
    private int port;
    private int conTimeout;

    public TCPNetworkModule(SocketFactory socketFactory, String host, int port) {
        this.socketFactory = socketFactory;
        this.host = host;
        this.port = port;
    }

    @Override
    public void start() throws IOException {

        SocketAddress socketAddress = new InetSocketAddress(host, port);

        socket = socketFactory.createSocket();
        socket.connect(socketAddress);

    }

    @Override
    public void stop() throws IOException {
        if (socket != null) {

            socket.shutdownInput();
            socket.close();
        }
    }

    @Override
    public InputStream getInputStream() throws Exception {
        return socket.getInputStream();
    }

    @Override
    public OutputStream getOutputStream() throws Exception {
        return socket.getOutputStream();
    }

    @Override
    public String getServerURI() {
        return "tcp://" + host + ":" + port;
    }

    public void setConTimeout(int conTimeout) {
        this.conTimeout = conTimeout;
    }
}
