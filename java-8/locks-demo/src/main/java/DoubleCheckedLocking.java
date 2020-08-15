import java.util.Date;

public class DoubleCheckedLocking {

    private static volatile DoubleCheckedLocking instance = null;
    private Date date = new Date();

    private DoubleCheckedLocking(){

    }

    public Date getDate(){
        return this.date;
    }

    public static  DoubleCheckedLocking getInstance(){
        if(instance == null){
            synchronized (DoubleCheckedLocking.class){
                if(instance == null){
                    instance = new DoubleCheckedLocking();
                }
            }
        }
        return instance;
    }
}
