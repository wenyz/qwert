package abattleground.jsonptest;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

public class TestObj {

    private String name;
    private int age;
    private String id;
    private long frameTime;
    @JSONField(deserializeUsing = GeoJsonPointDeserializer.class)
    private GeoJsonPoint obj;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getFrameTime() {
        return frameTime;
    }

    public void setFrameTime(long frameTime) {
        this.frameTime = frameTime;
    }

    public GeoJsonPoint getObj() {
        return obj;
    }

    public void setObj(GeoJsonPoint obj) {
        this.obj = obj;
    }
}
