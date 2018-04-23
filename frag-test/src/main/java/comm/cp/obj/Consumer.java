package comm.cp.obj;

public class Consumer {

    private String lock;

    public Consumer(String lock) {
        this.lock = lock;
    }

    public void getValue() throws InterruptedException {
        synchronized (lock) {

            if (ValueObject.value.equals("")) {
                System.out.println("consumer " + Thread.currentThread().getName() + "  has waiting : XXXXXXXXXXXXXX");
                lock.wait();
            }

            System.out.println("consumer " + Thread.currentThread().getName() + "has get the value : " + ValueObject.value);
            ValueObject.value = "";

            lock.notify();

        }
    }
}
