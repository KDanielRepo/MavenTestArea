public class Threading {
    public static void main(String[] args) {
        Print p = new Print();
        ThreadTest threadTest = new ThreadTest("A",p);
        ThreadTest threadTest1 = new ThreadTest("B",p);
        threadTest.start();
        threadTest1.start();
        try{
            threadTest.join();
            threadTest1.join();
        }catch (Exception e){
            System.out.println("err");
        }

    }
}
class ThreadTest extends Thread{
    private Thread t;
    private String name;
    Print p;
    public ThreadTest(){

    }
    public ThreadTest(String name,Print p){
        this.name = name;
        this.p = p;
    }
    @Override
    public void run() {
        synchronized (p){
            if(name.equals("A")){
                p.methodA();
            }else{
                p.methodB();
            }
        }
    }

    @Override
    public void start() {
        if(t==null){
            t = new Thread(this,name);
            t.start();
        }
    }
}

class Print{
    public void methodA(){
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
        }
    }
    public void methodB(){
        for (int i = 0; i < 5; i++) {
            System.out.println("B: "+i);
        }
    }
}
