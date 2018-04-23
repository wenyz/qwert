package comm.zk;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

public class SubscribeDataChanges {

    public static void main(String[] args) throws Exception {

        ZkClient zkClient = CreateSession.connectZK();

        zkClient.subscribeDataChanges("/wenNode", new ZKDataListener());

        Thread.sleep(Integer.MAX_VALUE);

    }

    private static class ZKDataListener implements IZkDataListener {

        public void handleDataChange(String s, Object o) throws Exception {
            System.out.println("Data has changed" + s + "   data:" + o.toString());
        }

        public void handleDataDeleted(String s) throws Exception {
            System.out.println("the path has deleted:" + s);
        }
    }


}
