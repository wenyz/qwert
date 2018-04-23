package comm.cp.obj;

public class Producer {

    private String lock;

    public Producer(String lock) {
        this.lock = lock;
    }

    public void setValue() throws InterruptedException {
        synchronized (lock) {
            if (!ValueObject.value.equals("")) {
                System.out.println("producer   " + Thread.currentThread().getName() + " is waiting : OOOOOOOO");
                lock.wait();

            }

            String value = System.currentTimeMillis() + "_" + System.nanoTime();
            System.out.println("producer   " + Thread.currentThread().getName() + "has set the value : " + value);
            ValueObject.value = value;
            lock.notify();
        }
    }
}
