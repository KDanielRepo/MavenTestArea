import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class Multi {
    Double test = 1.0;
    public void test(){
        for(int i = 0; i<1000;i++){
            test +=2;
            for(int j = 0; j <1000;j++){
                test = test/3.14;
            }
        }
    }
    public void test2(){
        for(int i = 0; i<1000;i++){
            test +=2;
            for(int j = 0; j <1000;j++){
                test = test/3.14;
            }
        }
    }
    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            synchronized (this){
                for(int i = 0; i<1000;i++){
                    test +=2;
                    for(int j = 0; j <1000;j++){
                        test = test/3.14;
                    }
                }
            }
        }
    },"test3");
    Thread thread2 = new Thread(new Runnable() {
        @Override
        public void run() {
            synchronized (this){
                for(int i = 0; i<1000;i++){
                    test +=2;
                    for(int j = 0; j <1000;j++){
                        test = test/3.14;
                    }
                }
            }
        }
    },"test4");

    public static void main(String[] args) {
        Instant start = Instant.now();
        Multi multi = new Multi(); //4.055694075395968E-50
        multi.thread.start();
        multi.thread2.start();
        /*multi.test();
        multi.test2();*/
        Instant stop = Instant.now();
        System.out.println(multi.test);
        System.out.println(Duration.between(start,stop).toNanos());
    }
}
