package comm.zk;

import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.apache.zookeeper.Watcher;

public class CreateSession {

    public static ZkClient connectZK() {

        String ZKServers = "localhost:2181";

        ZkClient zkClient = new ZkClient(ZKServers, 10000, 10000, new SerializableSerializer());
        zkClient.subscribeStateChanges(new IZkStateListener() {

            public void handleStateChanged(Watcher.Event.KeeperState keeperState) throws Exception {
                System.out.println("the state has changed:state:" + keeperState);
            }

            public void handleNewSession() throws Exception {
                System.out.println("handleNewSession()");
            }
        });
        System.out.println("connect OKKKKK");

        return zkClient;
    }
}
