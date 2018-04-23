package comm.cp.list;

public class StackConsumer {

    private StackObj stackObj;

    public StackConsumer(StackObj stackObj) {
        this.stackObj = stackObj;
    }

    public void popService() throws InterruptedException {
        stackObj.pop();
    }


}
