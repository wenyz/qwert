package abattleground.jsonptest;

import com.alibaba.fastjson.JSON;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {

    public static void main(String[] args) throws InterruptedException {

        long startTime = System.nanoTime();
//        String x = String.format("%.4f", 116 + (Math.random()/1000));
//        String y = String.format("%.4f", 39 + (Math.random()/1000));
//        String time = String.valueOf(System.currentTimeMillis());
//        String msg = "{\"deviceId\":\"000000000001\",\"deviceType\":\"12\",\"frameTime\":" + time + "," +
//                "\"loc\":{\"coordinates\":[" + x + "," + y + "],\"type\":\"Point\",\"x\":" + x + ",\"y\":" + y + "}}";

//        String msg = "{\"deviceId\":\"000000000001\",\"deviceType\":\"12\",\"frameTime\":1536201680689,\"loc\":{\"coordinates\":[116.463,39.9212],\"type\":\"Point\",\"x\":116.463,\"y\":39.9212}}";
//
//
        String msg = "{\"age\":10,\"frameTime\":1536564819288,\"id\":\"00000001\",\"name\":" +
                "\"qwertyfg\",\"obj\":{\"coordinates\":[116.463,39.9212],\"type\":\"Point\",\"x\":116.463,\"y\":39.9212}}";
//        int count = 1000000;
//        for (int i = 0; i < count; i++) {
//            JSON.parseObject(msg, TestObj.class);
//        }

        AtomicInteger count = new AtomicInteger(1000000);

        int threadCount = 100;

        CountDownLatch cdl = new CountDownLatch(threadCount);

        for (int i = 0; i <threadCount ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (count.get() > 0){
                        try( RandomAccessFile file = new RandomAccessFile("G:\\111.txt","r")) {
                            JSON.parseObject(file.readLine(), TestObj.class);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        count.decrementAndGet();
                    }
                    cdl.countDown();
                }
            }).start();
        }

        cdl.await();

        long endTime = System.nanoTime();

        String costTime = String.format("%.4f seconds",(endTime - startTime)/1e9)  ;

        System.out.println("execute " + count + " cost time: " + costTime);


        // 1.8 s
        // 1.9 s

//        TestObj aaa = new TestObj();
//        aaa.setAge(10);
//        aaa.setFrameTime(System.currentTimeMillis());
//        aaa.setId("00000001");
//        aaa.setName("qwertyfg");
//        aaa.setObj(new GeoJsonPoint(116.463,39.9212));
//
//        System.out.println(JSON.toJSONString(aaa));
//
    }
}
