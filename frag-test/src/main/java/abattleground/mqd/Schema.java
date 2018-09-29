package abattleground.mqd;


import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Schema {

    private SchemaOptions options;
    private Producer producer;
    private Consumer consumer;

    protected static Schema defaultSchema(String key){
        Schema schema = new Schema();
        schema.setOptions(SchemaOptions.defaultOptions());
        return new Schema();
    }

    public SchemaOptions getOptions() {
        return options;
    }

    public void setOptions(SchemaOptions options) {
        this.options = options;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    public static void main(String[] args) {
    }
}
