package abattleground;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


import sun.instrument.InstrumentationImpl;


// 运行本测试需要追加虚拟机参数 javaagent
// -javaagent:G:\WORK\qwert\frag-test\lib\size-of-object-1.0-SNAPSHOT.jar

public class sizeobj {

    public static void main(String[] args) throws IllegalAccessException {

//        SizeOfObject ss = new SizeOfObject();
//        SizeOfObject.premain("1111",new InstrumentationImpl());
        Map<String,String> sss = new HashMap<>();

        System.out.println(new File("target/classes").getAbsolutePath());
//        System.out.println("sizeOf(new Object())=" + sizeOf(sss));
    }
}
