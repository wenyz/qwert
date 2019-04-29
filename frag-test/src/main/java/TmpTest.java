import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.BitSet;

public class TmpTest {

    public static void main(String[] args) {


//        int i =10;
//        System.out.println((byte)(i &0xFF));


//            int aaa = 0x03;
//            int bbb = 3;
//
//            if(aaa==bbb){
//                System.out.println("kkkk");
//            }else System.out.println("ggggg");
//
//
//            Integer.toHexString(aaa);

//        byte[] kkk = {(byte) 0xdf, 0x00, 0x00, 0x43, 0x10, 0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77, 0x33};
//
//        System.out.println(Integer.toHexString(kkk[0] >> 7 & 0x01));
//        System.out.println(Integer.toHexString(kkk[0] >> 6 & 0x01));
//        System.out.println(Integer.toHexString(kkk[0] >> 4 & 0x03));
//        System.out.println(Integer.toHexString(kkk[0] &

//        int aa = 0x304;
//        byte[] buf = new byte[4];
//
//        buf[0] = (byte) (aa >> 24 & 0xFF);
//        buf[1] = (byte) (aa >> 16 & 0xFF);
//        buf[2] = (byte) (aa >> 8 & 0xFF);
//        buf[3] = (byte) (aa & 0xFF);
//
//        System.out.println("ttt" + Integer.toHexString(aa));
//
//        int bbb = 0x301;
//        System.out.println("bbbbb" + Integer.toHexString(bbb));
//        System.out.println("bbbbb" + bbb);
//
//        int aae = 769;
//        System.out.println("gggg" + Integer.toHexString(aae));

//


//        long start = System.currentTimeMillis();
//
//        byte[] kkk = {(byte) 0xee, 0x3f, 0x4d, 0x22};
//
//        for (int i = 0; i < 1e9; i++) {
//            int k = kkk[0] << 24 | kkk[1] << 16 | kkk[2] << 8 | kkk[3];
//        }
//
//        long end = System.currentTimeMillis();
//        //1329
//        System.out.println("cost time:" + (end - start));


        //long start = System.currentTimeMillis();
        //String[] kkk = {"EE", "3F", "4D", "22"};
        //String kkk = "1E3F4D22";
        //        byte[] kkk1 = {(byte) 0x1e, 0x3f, 0x4d, 0x22};
//        int kg = kkk1[0] << 24 | kkk1[1] << 16 | kkk1[2] << 8 | kkk1[3];
//
//        System.out.println("kgkgkg"+kg);
//
//        System.out.println(Integer.parseInt("1E3F4D22",16));


//        String kkk = "1E3F4D22";
//
//        for (int i = 0; i < 1e9; i++) {//1335 //20570
//            int k = Integer.parseInt(kkk,16);
//        }
//
//        long end = System.currentTimeMillis();
//
//        System.out.println("cost time:" + (end - start));


//        long start = System.currentTimeMillis();
//
//        byte[] kkk = {(byte) 0xee, 0x3f, 0x4d, 0x22};
//
//        for (int i = 0; i < 1e9; i++) {
//            int k = kkk[0] << 24 | kkk[1] << 16 | kkk[2] << 8 | kkk[3];
//        }
//
//        long end = System.currentTimeMillis();
//        //1329
//        System.out.println("cost time:" + (end - start));

//        int x = 10;
//        int y=20;
//        change(x,y);
//        System.out.println("x="+x);
//        System.out.println("y="+y);
//
//        String ip = "192.168.10.174";
//
//        String[] ips = ip.split(".");
//
//        int k = 0;
//        for (int i = 1; i < 5; i++) {
//            k= k|Integer.valueOf(ips[i]) << 8 * i;
//        }


        //byte[] gg = {0,0,0,0,0,0,0,0};

        Integer k  =100;

        int p = 0b0000011000000100;
        byte[] ef = {0b00000110,0b00000100};
        int kg = (int)ef[1]<<8|(int)ef[0];


        System.out.println(p);








    }
    /**
     * 字节数组到long的转换.
     */
    public static long byteToLong(byte[] b) {
        long s = 0;
        long s0 = b[0] & 0xff;// 最低位
        long s1 = b[1] & 0xff;
        long s2 = b[2] & 0xff;
        long s3 = b[3] & 0xff;
        long s4 = b[4] & 0xff;// 最低位
        long s5 = b[5] & 0xff;
        long s6 = b[6] & 0xff;
        long s7 = b[7] & 0xff;

        // s0不变
        s1 <<= 8;
        s2 <<= 16;
        s3 <<= 24;
        s4 <<= 8 * 4;
        s5 <<= 8 * 5;
        s6 <<= 8 * 6;
        s7 <<= 8 * 7;
        s = s0 | s1 | s2 | s3 | s4 | s5 | s6 | s7;
        return s;
    }



    private static void change(int x, int y) {
        int z;
        z = x;
        x = y;
        y = z;

        System.out.println("inside x=" + x);
        System.out.println("inside y=" + y);

    }
}
