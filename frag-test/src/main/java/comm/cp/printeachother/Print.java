package comm.cp.printeachother;

public class Print {

    public static void main(String[] args) {
//        new Thread(new PrintRunnable(1)).start();
//        new Thread(new PrintRunnable(2)).start();
//        new Thread(new PrintRunnable(3)).start();

        for (int i = 0; i < 1000; i++) {
            new Thread(new PrintRunnable(i)).start();
        }
    }
}

class PrintRunnable implements Runnable {

    private static int printNum = 0;
    private int threadId;

    public PrintRunnable(int threadId) {
        this.threadId = threadId;
    }

    @Override
    public void run() {
        while (printNum < 10000000) {
            synchronized (Print.class) {
                //  System.out.println("jinru " + threadId);
                // System.out.println("jiegou+++" + (printNum / 5 % 3 + 1));

                if (printNum / 10000 % 1000 + 1 == threadId) {
                    //    System.out.println("jjjjjinru " + threadId);
                    for (int i = 0; i < 5; i++) {
                        System.out.println("线程" + threadId + ":" + (++printNum));
                    }
                    Print.class.notifyAll();
                } else {
                    try {
                        //     System.out.println("wait " + threadId);
                        Print.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }
}