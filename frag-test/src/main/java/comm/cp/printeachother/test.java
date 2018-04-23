package comm.cp.printeachother;

public class test {

    public static void main(String[] args) {

        ThreadPrint threadPrint = new ThreadPrint();

        for (int i = 0; i < 20; i++) {

            Thread A = new Thread(new ThreadA(threadPrint));
            Thread B = new Thread(new ThreadB(threadPrint));
            A.start();
            B.start();

        }


    }
}
