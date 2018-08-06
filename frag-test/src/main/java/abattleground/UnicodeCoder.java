package abattleground;

import java.util.Stack;

public class UnicodeCoder {

    // 将Unicode码转换为中文
    public static String tozhCN(String unicode) {
        StringBuffer gbk = new StringBuffer();
        String hex[] = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++) { // 注意要从 1 开始，而不是从0开始。第一个是空。
            int data = Integer.parseInt(hex[i], 16); // 将16进制数转换为 10进制的数据。
            gbk.append((char) data); // 强制转换为char类型就是我们的中文字符了。
        }
        //System.out.println("这是从 Unicode编码 转换为 中文字符: " + gbk.toString());
        return gbk.toString();
    }

    public static void main(String[] args) {

        Stack<String> result = new Stack<>();

        String aaa = "80771F6B637684597D670B53CBFF0C5E764E0D662F57284E008D776709804A4E0D5B8C76848BDDFF1B800C662F57284E008D77FF0C5C317B974E0D8BF48BDDFF0C4E5F4E0D4F1A89C95F975C345C2C3002";

        //String aaa = args[0];
        String prefix = "\\u";

        int j = aaa.length();

        for (int i = aaa.length(); i > 0; i = i - 4) {

            String tmp = aaa.substring(i, j);
            j = i;
            // System.out.println(tmp);
            result.push(tozhCN(prefix + tmp));
        }

        String cat = "";
        while (!result.isEmpty()) {
            cat = cat + result.pop();
        }
        System.out.println(cat);

        //tozhCN("\\u5C2C");
    }
}
