package utils.encode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class EncodeUTF8 {

    public static void main(String[] args) throws Exception {

        int i = -1024;

        byte j = (byte) (i >>> 8 & 0xFF);

        System.out.println(Integer.toBinaryString(i));
        System.out.println(Integer.toBinaryString(j));

        byte[] encodedString = "⭐".getBytes("UTF-8");
        for (byte temp : encodedString) {
            System.out.println(Byte.toString(temp));

        }

        int tt = 0xff;

        System.out.println("ttt" + tt);


    }

    protected void encodeUTF8(DataOutputStream dos, String stringToEncode) throws Exception {
        try {

            byte[] encodedString = stringToEncode.getBytes("UTF-8");
            byte byte1 = (byte) ((encodedString.length >>> 8) & 0xFF);
            byte byte2 = (byte) ((encodedString.length >>> 0) & 0xFF);


            dos.write(byte1);
            dos.write(byte2);
            dos.write(encodedString);
        } catch (UnsupportedEncodingException ex) {
            throw new Exception(ex);
        } catch (IOException ex) {
            throw new Exception(ex);
        }
    }
}
