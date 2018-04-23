package comm.cp.list;

import java.util.ArrayList;
import java.util.List;

public class StackObj {

    private List<String> channel = new ArrayList<>();
    private Object pushpopLock = new Object();

    public void push(String obj) throws InterruptedException {

        synchronized (pushpopLock) {

            while (channel.size() > 1000) {
                System.out.println("=====================================");
                System.out.println("push is waiting");
                pushpopLock.wait();
            }
            this.channel.add(obj);
            System.out.println("push is notify");
            System.out.println("list size is :" + channel.size());
            pushpopLock.notifyAll();
        }

    }

    public String pop() throws InterruptedException {

        String value = "";

        short aaa = 1;
        synchronized (pushpopLock) {

            while (channel.size() <= 0) {
                System.out.println("=====================================");
                System.out.println("pop is waiting");
                pushpopLock.wait();
            }

            value = channel.get(channel.size() - 1);
            channel.remove(channel.size() - 1);
            System.out.println("list size is :" + channel.size());
            System.out.println("pop is notify");
            pushpopLock.notifyAll();

            return value;
        }

    }

}
