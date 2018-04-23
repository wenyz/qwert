package comm.cp.thread;


import comm.cp.list.StackProducer;

public class ThreadProducer extends Thread {

    private StackProducer producer;

    public ThreadProducer(StackProducer producer) {
        super();
        this.producer = producer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                producer.pushService();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
