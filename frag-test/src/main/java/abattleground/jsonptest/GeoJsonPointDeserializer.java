package abattleground.jsonptest;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * @author wenyz
 */
public class GeoJsonPointDeserializer implements ObjectDeserializer {
    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {

        Map map = parser.parseObject(Map.class);
        GeoJsonPoint result = new GeoJsonPoint(Double.valueOf(map.get("x").toString()), Double.valueOf(map.get("y").toString()));
        return (T) result;
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }
}
