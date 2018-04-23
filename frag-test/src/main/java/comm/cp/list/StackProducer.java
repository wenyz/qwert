package comm.cp.list;

public class StackProducer {

    private StackObj stackObj;

    public StackProducer(StackObj stackObj) {
        this.stackObj = stackObj;
    }

    public void pushService() throws InterruptedException {
        stackObj.push(String.valueOf(Math.random()));
    }
}
