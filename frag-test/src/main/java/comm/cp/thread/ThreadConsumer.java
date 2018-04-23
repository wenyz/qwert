package comm.cp.thread;


import comm.cp.list.StackConsumer;

public class ThreadConsumer extends Thread {

    private StackConsumer consumer;

    public ThreadConsumer(StackConsumer consumer) {
        super();
        this.consumer = consumer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                consumer.popService();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
