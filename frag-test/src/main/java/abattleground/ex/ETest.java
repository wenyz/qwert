package abattleground.ex;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import static java.lang.Thread.sleep;

public class ETest {

    public static void main(String[] args) {

//        final long per = 1000;
//
//        ScheduledExecutorService ss = Executors.newScheduledThreadPool(1);
//
//        ss.schedule(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("gggg");
//            }
//        }, per, TimeUnit.MILLISECONDS);

//        int n = 0;
//        System.out.println(++n);

        Thread printThread = new Thread(new Runnable() {
            @Override
            public void run() {
          //      while (true){
                    System.out.println("print aaa aaa");
                    try {
                        sleep(10000);

                        System.out.println("block the print thread");
//                        LockSupport.park();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
        //        }
            }
        });

        printThread.start();

        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("now is here");
        LockSupport.unpark(printThread);

    }

}
