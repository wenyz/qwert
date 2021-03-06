package comm.mqtt.ssl;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

public class PahoTlsExample {

    public static void main(String[] args) {

        connect();
    }


    private static void connect() {

        String topic = "MQTT Examples";
        String content = "GGGGG";
        int qos = 2;
        //String broker = "ssl://121.43.180.66:8883";
        String broker = "ssl://192.168.111.138:8883";
        String clientId = "JavaSample";
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            SSLSocketFactory factory = null;
            try {
                //factory = getSSLSocktet("D:/cacert.crt", "D:/caclient2.crt", "D:/client2-pkcs7.pem", "");
                factory = getSSLSocktet("D:/emqssl/cacert.crt", "D:/emqssl/client-cert.crt", "D:/emqssl/client-key-pkcs8.pem", "brt123");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            connOpts.setSocketFactory(factory);
            System.out.println("Connecting to broker: " + broker);
            sampleClient.connect(connOpts);
            System.out.println("Connected");
            System.out.println("Publishing message: " + content);
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            sampleClient.publish(topic, message);
            System.out.println("Message published");

            while (true) {


                sampleClient.setCallback(new MqttCallback() {
                    @Override
                    public void connectionLost(Throwable cause) {

                    }

                    @Override
                    public void messageArrived(String topic, MqttMessage message) throws Exception {
                        System.out.println("MSGMSGMSGMSGMSGMSGMSGMSG" + new String(message.getPayload()));
                    }

                    @Override
                    public void deliveryComplete(IMqttDeliveryToken token) {

                    }
                });

                sampleClient.subscribe("testssl", 2);

                Thread.sleep(2000);
            }


            // sampleClient.disconnect();
//            System.out.println("Disconnected");
//            System.exit(0);
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static SSLSocketFactory getSSLSocktet(String caPath, String crtPath, String keyPath, String password) throws Exception {
        // CA certificate is used to authenticate server
        CertificateFactory cAf = CertificateFactory.getInstance("X.509");
        FileInputStream caIn = new FileInputStream(caPath);
        X509Certificate ca = (X509Certificate) cAf.generateCertificate(caIn);
        KeyStore caKs = KeyStore.getInstance("JKS");
        caKs.load(null, null);
        caKs.setCertificateEntry("ca-certificate", ca);
        TrustManagerFactory tmf = TrustManagerFactory.getInstance("PKIX");
        tmf.init(caKs);

        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        FileInputStream crtIn = new FileInputStream(crtPath);
        X509Certificate caCert = (X509Certificate) cf.generateCertificate(crtIn);

        crtIn.close();
        // client key and certificates are sent to server so it can authenticate
        // us
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        ks.load(null, null);
        ks.setCertificateEntry("certificate", caCert);
        ks.setKeyEntry("private-key", getPrivateKey(keyPath), password.toCharArray(),
                new java.security.cert.Certificate[]{caCert});
        KeyManagerFactory kmf = KeyManagerFactory.getInstance("PKIX");
        kmf.init(ks, password.toCharArray());

        // finally, create SSL socket factory
        SSLContext context = SSLContext.getInstance("TLSv1");

        context.init(kmf.getKeyManagers(), tmf.getTrustManagers(), new SecureRandom());

        return context.getSocketFactory();
    }

    public static PrivateKey getPrivateKey(String path) throws Exception {

        org.apache.commons.codec.binary.Base64 base64 = new org.apache.commons.codec.binary.Base64();
        byte[] buffer = base64.decode(getPem(path));

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);

    }

    private static String getPem(String path) throws Exception {
        FileInputStream fin = new FileInputStream(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(fin));
        String readLine = null;
        StringBuilder sb = new StringBuilder();
        while ((readLine = br.readLine()) != null) {
            if (readLine.charAt(0) == '-') {
                continue;
            } else {
                sb.append(readLine);
                sb.append('\r');
            }
        }
        fin.close();
        return sb.toString();
    }
}
