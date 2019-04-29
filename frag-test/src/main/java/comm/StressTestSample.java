package comm;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * 高并发测试样本代码
 *
 */
public class StressTestSample {


    private int presureNum = 10000;
    private CountDownLatch cdl = new CountDownLatch(presureNum);

    public void preureTest(){

        for (int i = 0; i <presureNum ; i++) {
            new Thread(new Test()).start();
            cdl.countDown();
        }
        try {
            Thread.currentThread().sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    class Test implements Runnable{

        @Override
        public void run() {
            try {
                cdl.await();
                System.out.println("11111111111");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){

        StressTestSample sts = new StressTestSample();

        sts.preureTest();
    }
}
