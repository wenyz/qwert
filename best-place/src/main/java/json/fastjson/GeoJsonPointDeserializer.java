//package json.fastjson;
//
//import com.alibaba.fastjson.parser.DefaultJSONParser;
//import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
//import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
//
//import java.lang.reflect.Type;
//import java.util.Map;
//
///**
// * @author wenyz
// */
//public class GeoJsonPointDeserializer implements ObjectDeserializer {
//    @Override
//    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
//
//        Map map = parser.parseObject(Map.class);
//
//        System.out.println("gggggggg" + parser.getLexer().getCurrent());
//        System.out.println("kkkkkkkkkkkkk");
//        GeoJsonPoint result = new GeoJsonPoint(Double.valueOf(map.get("x").toString()), Double.valueOf(map.get("y").toString()));
//        return (T) result;
//    }
//
//    @Override
//    public int getFastMatchToken() {
//        return 0;
//    }
//}

//===============================================================
//fast json 自定义 反序列化
// 1 在相关类上加     @JSONField(deserializeUsing = GeoJsonPointDeserializer.class) 注解
// 2 如上实现
