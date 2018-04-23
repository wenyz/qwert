package comm.cp.printeachother;

public class ThreadB implements Runnable {

    ThreadPrint threadPrint;

    public ThreadB(ThreadPrint threadPrint) {
        this.threadPrint = threadPrint;
    }

    @Override
    public void run() {

        while (true) {
            try {
                threadPrint.printB();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
