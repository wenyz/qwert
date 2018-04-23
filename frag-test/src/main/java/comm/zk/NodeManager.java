package comm.zk;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

public class NodeManager {

    public static void main(String[] args) {

        NodeManager nm = new NodeManager();
        // nm.createNode();
        ZkClient zkClient = CreateSession.connectZK();
        for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setId(i);
            user.setName("test" + i);
            nm.updateNode(user, i, zkClient);
        }


        //nm.deleteNode();
    }

    public void createNode() {

        ZkClient zkClient = CreateSession.connectZK();

        User user = new User();
        user.setId(1);
        user.setName("wen001");


        String path = zkClient.create("/wenNode", user, CreateMode.PERSISTENT);


        System.out.println("path has created:" + path);

    }

    public void updateNode(User user, int i, ZkClient zkClient) {
        //ZkClient zkClient = CreateSession.connectZK();
        zkClient.create("/wenNode", user, CreateMode.PERSISTENT_SEQUENTIAL);
        //zkClient.writeData("/wenNode" + "/" + i, user);
        // zkClient.writeData("/wenNode", user);
        System.out.println("succeed updated dataNode");

    }

    public void deleteNode() {

        ZkClient zkClient = CreateSession.connectZK();

        boolean successFlg = zkClient.delete("/wenNode");

        if (successFlg) System.out.println("delete the node success");

    }
}
