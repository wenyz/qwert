package comm.cp.printeachother;

public class ThreadPrint {

    private volatile boolean isPrintA = true;
    private Object printLock = new Object();

    public void printA() throws Exception {

        synchronized (printLock) {
            while (!isPrintA) printLock.wait();

            isPrintA = false;
            System.out.println("AAAAA");
            printLock.notifyAll();
        }


    }

    public void printB() throws Exception {

        synchronized (printLock) {
            while (isPrintA) printLock.wait();

            isPrintA = true;
            System.out.println("BBBBB");
            printLock.notifyAll();
        }


    }

}
