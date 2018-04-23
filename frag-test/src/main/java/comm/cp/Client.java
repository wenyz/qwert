package comm.cp;

import comm.cp.list.StackConsumer;
import comm.cp.list.StackObj;
import comm.cp.list.StackProducer;
import comm.cp.thread.ThreadConsumer;
import comm.cp.thread.ThreadProducer;

public class Client {

    public static void main(String[] args) throws InterruptedException {
        StackObj stackObj = new StackObj();

        StackProducer producer = new StackProducer(stackObj);
        StackConsumer consumer = new StackConsumer(stackObj);

        int threadCount = 10;

        ThreadProducer[] threadProducer = new ThreadProducer[threadCount];
        ThreadConsumer[] threadConsumer = new ThreadConsumer[threadCount];

        for (int i = 0; i < threadCount; i++) {
            threadProducer[i] = new ThreadProducer(producer);
            threadProducer[i].setName("生产者：" + i);
            threadConsumer[i] = new ThreadConsumer(consumer);
            threadConsumer[i].setName("消费者：" + i);

            threadProducer[i].start();
            threadConsumer[i].start();
        }

//        Thread.sleep(5000);
//
//        Thread[] threads = new Thread[Thread.currentThread().getThreadGroup().activeCount()];
//
//        Thread.currentThread().getThreadGroup().enumerate(threads);
//
//        for (int i = 0; i < threads.length; i++) {
//
//            System.out.println("TTTTTTTTTTTTTTTTT"+threads[i].getName() + threads[i].getState());
//        }


    }
}
