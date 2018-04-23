package comm.as.internal;

import comm.as.Server;

import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public class ServerState {

    private final Map<String, UserData> data;
    private final Vector<UserData> syncData;
    private final Vector<UserData> remainData;
    private Object syncLock = new Object();
    private Object remainLock = new Object();

    private Server server;

    public ServerState() {
        this.data = new ConcurrentHashMap<String, UserData>();
        this.syncData = new Vector<>();
        this.remainData = new Vector<>();
    }


    public void push(UserData userData) throws InterruptedException {

        synchronized (syncLock) {
            while (syncData.size() > 1000) syncLock.wait();
            syncData.add(userData);
            syncLock.notifyAll();
            System.out.println("server " + server.getName() + " has push an element!! XXXXXXXXX the size is now ::" + syncData.size());
        }

    }

    public UserData pop() throws InterruptedException {
        UserData userData = null;
        synchronized (syncLock) {
            while (syncData.size() <= 0) syncLock.wait();
            userData = syncData.get(syncData.size() - 1);
            syncData.remove(syncData.size() - 1);
            syncLock.notifyAll();
            System.out.println("server " + server.getName() + " has pop an element!! OOOOO the size is now ::" + syncData.size());
        }
        return userData;
    }

    public void addData(UserData userData) {
        data.put(userData.getUserId(), userData);
    }
}
