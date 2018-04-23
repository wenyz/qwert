package as;

import java.net.SocketAddress;

public interface Server {

    void start();

    void stop();

    void addSlave(SocketAddress address);

    String getName();
}
