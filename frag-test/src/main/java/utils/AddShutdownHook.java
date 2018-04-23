package utils;

public class AddShutdownHook {

    public static void main(String[] args) throws InterruptedException {

        Thread.currentThread().sleep(100000);

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("kkkkkkk");
            }
        }
        ));


    }
}
