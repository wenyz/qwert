package abattleground.mqd;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class QueuesCache {

    private static final Map<String,Schema> schemas = new ConcurrentHashMap<>();

    public static synchronized Producer getProducerBySchema(String key){
        Schema schema = schemas.get(key);
        Producer producer = null;

        if(schema != null){
            producer = schema.getProducer();
        }

        return producer;
    }

    public static Schema newSchema(String key,Consumer consumer){
        return newSchema(key,SchemaOptions.defaultOptions(),consumer);
    }

    public static Schema newSchema(String key,SchemaOptions options,Consumer consumer){
        return null;
    }


}
