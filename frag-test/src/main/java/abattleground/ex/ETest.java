package abattleground.ex;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
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

//        Thread printThread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//          //      while (true){
//                    System.out.println("print aaa aaa");
//                    try {
//                        sleep(10000);
//
//                        System.out.println("block the print thread");
////                        LockSupport.park();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//        //        }
//            }
//        });
//
//        printThread.start();
//
//        try {
//            sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("now is here");
//        LockSupport.unpark(printThread);
//
//    }


        Map<String, Integer> map=new HashMap<>();
        map.put("张三",22);
        map.put("李四",25);
        map.put("王五",33);
        map.put("赵六",28);
        map.put("田七",25);
        map.put("李思",25);
        map.put("李嘉欣",25);

        Set<Map.Entry<String, Integer>> set=map.entrySet();

        for (Map.Entry<String, Integer> entry : set) {
            String name=entry.getKey();
            System.out.println(name);
            System.out.println(name.contains("李"));
            if(name.contains("李")){
                map.remove(name);
            }
        }

    }

}
