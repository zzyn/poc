import java.util.Date;

public class InitializationOnDemandHolder {

    private static class Holder {
        public static InitializationOnDemandHolder instance = new InitializationOnDemandHolder();
    };


    private InitializationOnDemandHolder(){

    }

    private Date date = new Date();

    public Date getDate(){
        return this.date;
    }

    public static  InitializationOnDemandHolder getInstance(){
        return Holder.instance;
    }
}
