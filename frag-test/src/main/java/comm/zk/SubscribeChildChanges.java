package comm.zk;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;

public class SubscribeChildChanges {

    public static void main(String[] args) throws Exception {
        ZkClient zkClient = CreateSession.connectZK();

        zkClient.subscribeChildChanges("/wenNode", new ZKChildListener());

        Thread.sleep(Integer.MAX_VALUE);
    }

    private static class ZKChildListener implements IZkChildListener {

        public void handleChildChange(String s, List<String> list) throws Exception {
            System.out.println("the child node has changes" + s + list.get(0).toString());
        }
    }
}
