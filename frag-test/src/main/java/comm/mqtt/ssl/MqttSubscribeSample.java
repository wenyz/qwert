package comm.mqtt.ssl;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class MqttSubscribeSample {

    public static void main(String[] args) {
        String topic = "device/+/sc01/status";

        //String topic = "ptest/+";

        String content = "Message from MqttPublishSample";
        int qos = 2;
        String broker = "tcp://192.168.20.249:1883";
        String clientId = "JavaSample";


        int threadCount = 10;
       // List<>

      //  for (int i = 0; i <threadCount ; i++) {

            try {
                MemoryPersistence persistence = new MemoryPersistence();
                MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
                MqttConnectOptions connOpts = new MqttConnectOptions();
                connOpts.setCleanSession(true);
                System.out.println("Connecting to broker: " + broker);

                //connOpts.setWill("/online/test", "i was ggggg line".getBytes(), 2, false);
                connOpts.setUserName("admin");
                connOpts.setPassword("public".toCharArray());

                sampleClient.connect(connOpts);
                sampleClient.subscribe(topic,new MqttTestListener(new AtomicInteger()){});
                new CountDownLatch(1).await();
            } catch (Exception e) {
                e.printStackTrace();
            }
    //    }



    }

    static class MqttTestListener implements IMqttMessageListener{

        private AtomicInteger count;

        public MqttTestListener(AtomicInteger count) {
            this.count = count;
        }

        @Override
        public void messageArrived(String topic, MqttMessage message) throws Exception {
            System.out.println(count.addAndGet(1));
        }
    }

    static class SubscribeThread extends Thread{


        @Override
        public void run() {
            while (true){

            }
        }
    }
}
