package Utils;

public class TimeUtils {
    public static void wait(int ms){
        try{
            Thread.sleep(ms);
        } catch (InterruptedException e){
            System.out.println("There was an error on wait's method" + e.getMessage());
        }
    }
}
