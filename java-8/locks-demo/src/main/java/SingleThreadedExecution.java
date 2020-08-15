import java.util.Date;

public class SingleThreadedExecution {

    private static SingleThreadedExecution instance = null;
    private Date date = new Date();

    private SingleThreadedExecution(){

    }

    public Date getDate(){
        return this.date;
    }

    public static synchronized SingleThreadedExecution getInstance(){
        if(instance == null){
            instance = new SingleThreadedExecution();
        }
        return instance;
    }
}
