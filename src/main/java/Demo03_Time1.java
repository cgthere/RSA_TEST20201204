import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Demo03_Time1 {

    public static void main(String[] args) {
        final long timaInterval = 1;
        Runnable runnable = new Runnable() {
            public void run() {
                while (true) {
                    System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss: SSS").format(new Date())+"---"+new SimpleDateFormat("yyyyMMdd HHmmssSSS").format(new Date()));
                    try {
                        Thread.sleep(timaInterval);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

    }
}
