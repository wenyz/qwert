package utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Configs {


    public final static String configFile = System.getProperty("user.dir") + File.separator
            + "hmiConfig.properties";

    public static Properties loadConfig() throws IOException {
        File file = new File(configFile);
        Properties properties;
        if(file.exists() == false){
            properties = Configs.createProperties();
        }else{
            properties = new Properties();
            FileReader reader = new FileReader(file);
            properties.load(reader);
            reader.close();
        }
        return properties;
    }

    public static Properties createProperties(){
        Properties result = new Properties();
        result.setProperty(keys.zkConnectString.toString(),"aaaaaa");
        result.setProperty(keys.rootPath.toString(),"aaaaaa");
        result.setProperty(keys.userName.toString(),"aaaaaa");
        result.setProperty(keys.password.toString(),"aaaaaa");
        result.setProperty(keys.zkSessionTimeout.toString(),"aaaaaa");
        result.setProperty(keys.isCheckParentPath.toString(),"aaaaaa");

        return result;
    }

    public enum keys {
        zkConnectString, rootPath, userName, password, zkSessionTimeout, isCheckParentPath
    }
}
