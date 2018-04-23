package comm.mqtt.ssl;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.cert.CertificateException;

public class MqttPublishSample {


    public static void main(String[] args) throws KeyManagementException, CertificateException, FileNotFoundException, IOException, KeyStoreException {

        //String topic = "MQTT Examples";
        String topic = "fence/device/out/v1";

        String content = "Message from MqttPublishSample";
        int qos = 2;
        String broker = "tcp://121.43.180.66:1883";
        String clientId = "JavaSample";
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            System.out.println("Connecting to broker: " + broker);

            connOpts.setWill("/online/test", "i was ggggg line".getBytes(), 2, false);
            connOpts.setUserName("admin");
            connOpts.setPassword("admin".toCharArray());
//            connOpts.setSocketFactory(new SSLSocketFactoryImpl());
            sampleClient.connect(connOpts);
            System.out.println("Connected");
            System.out.println("Publishing message: " + content);
            MqttMessage message = new MqttMessage("1111".getBytes());
            message.setQos(qos);
            message.setRetained(false);
            sampleClient.publish(topic, message);
            System.out.println("Message published");

//            while (true) {
//
//                sampleClient.setCallback(new MqttCallback() {
//                    @Override
//                    public void connectionLost(Throwable cause) {
//
//                    }
//
//                    @Override
//                    public void messageArrived(String topic, MqttMessage message) throws Exception {
//                        System.out.println("noSSLnoSSLnoSSLnoSSLnoSSLnoSSLnoSSLnoSSLnoSSLnoSSLnoSSL" + new String(message.getPayload()));
//                    }
//
//                    @Override
//                    public void deliveryComplete(IMqttDeliveryToken token) {
//
//                    }
//                });
//                sampleClient.subscribe("testssl", 2);
//
//                Thread.sleep(5000);
//            }

//
// .disconnect();
//            System.out.println("Disconnected");
//            System.exit(0);
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
