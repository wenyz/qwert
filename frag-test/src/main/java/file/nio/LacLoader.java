package file.nio;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LacLoader {
    private static final List<Socket> socketList = Collections.synchronizedList(new ArrayList<Socket>());

    private LacLoader() {

    }

    public static List<Socket> getSocketList() throws Exception {

        if (socketList == null || socketList.size() == 0) {
            Socket socket = new Socket("localhost", 10032);
            socketList.add(socket);
        }
        return socketList;
    }
}
