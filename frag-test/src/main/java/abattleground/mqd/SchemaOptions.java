package abattleground.mqd;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SchemaOptions {

    private int consumerNumber = 1;
    private boolean sortable = false;
    private ExecutorService executors;
    private QueueSorter sorter;

    protected static SchemaOptions defaultOptions(){

        SchemaOptions options = new SchemaOptions();
        options.setConsumerNumber(1);
        options.setExecutors(Executors.newCachedThreadPool());
        options.setSortable(false);
        return options;
    }

    public void refreshOptions(){

    }



    public void setConsumerNumber(int consumerNumber) {
        this.consumerNumber = consumerNumber;
    }

    public boolean isSortable() {
        return sortable;
    }

    public void setSortable(boolean sortable) {
        if(sortable){
            sorter = new DefaultSorter();
        }
        this.sortable = sortable;
    }

    public ExecutorService getExecutors() {
        return executors;
    }

    public void setExecutors(ExecutorService executors) {
        this.executors = executors;
    }

    static class DefaultSorter implements QueueSorter{

        @Override
        public void sort() {

        }
    }
}
