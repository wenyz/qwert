package comm.mqtt.ssl;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

public class PressureTest {


    public static void main(String[] args) {


        List<MqttClient> clients = new ArrayList<>();

        String topic = "ptest/";

        String content = "Message from MqttPublishSample";
        int qos = 2;
        String broker = "tcp://192.168.20.249:1883";
        String clientId = "JavaSamplepub";

        List<PressThread> threads = new ArrayList<>();
        int threadCount = 200;
        AtomicInteger counter = new AtomicInteger();

        for (int i = 0; i <threadCount ; i++) {
            try {
                MemoryPersistence persistence = new MemoryPersistence();
                MqttClient sampleClient = new MqttClient(broker, clientId + i, persistence);
                MqttConnectOptions connOpts = new MqttConnectOptions();
                connOpts.setCleanSession(true);
                connOpts.setUserName("admin");
                connOpts.setPassword("public".toCharArray());
                sampleClient.connect(connOpts);
                PressThread tmp = new PressThread(sampleClient,counter,topic+"1");
                threads.add(tmp);
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }

        System.out.println("======================init thread ===========================");

        long startTime = System.currentTimeMillis();
        for(PressThread thread:threads){
            thread.start();
        }
        int number = 150000;
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Thread terminalThread = new Thread(new Runnable() {

            @Override
            public void run() {
                while (counter.get() < number){
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Iterator<PressThread> iterators = threads.iterator();
                while (iterators.hasNext()){
                    iterators.next().running = false;
                }
                countDownLatch.countDown();
            }
        });
        terminalThread.start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long stopTime = System.currentTimeMillis();
        System.out.println("=============花费时间 :" + (stopTime - startTime));
        System.out.println("=============发送条数 :" + counter.get());
    }

    static class PressThread extends Thread{

        MqttClient client;
        AtomicInteger counter;
        String topic;
        byte[] msgbyte = "1".getBytes();
        volatile boolean running = true;

        public PressThread(MqttClient client, AtomicInteger counter,String topic) {
            this.client = client;
            this.counter = counter;
            this.topic = topic;
        }

        @Override
        public void run() {
            while (running){
                try {
                    client.publish(topic,msgbyte,0,false);
                    counter.getAndIncrement();
                    sleep(1);
                } catch (MqttException e) {
                    e.printStackTrace();
                }
                catch (InterruptedException e) {
                    //e.printStackTrace();
                    //System.out.println("thread has stop");
                }
            }
            try {
                client.disconnect();
               // client.close();
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }
}
