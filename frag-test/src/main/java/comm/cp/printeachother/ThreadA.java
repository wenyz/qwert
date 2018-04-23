package comm.cp.printeachother;

public class ThreadA implements Runnable {

    ThreadPrint threadPrint;

    public ThreadA(ThreadPrint threadPrint) {
        this.threadPrint = threadPrint;
    }

    @Override
    public void run() {

        while (true) {

            try {
                threadPrint.printA();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
