import java.util.*;

public class SetsTest{
    Scanner scanner = new Scanner(System.in);
    List<String> a = new ArrayList<>();
    Map<String,String> c = new HashMap<>();
    public void startMap(){
        while (true){
            String b = scanner.next();
            String e = scanner.next();
            if(b.equals("-") || e.equals("-")){
                return;
            }
            c.put(b,e);
        }
    }
    public void stopMap(){
        String b = scanner.next();
        for(String m : c.keySet()){
            if(b.equals(m)){
                System.out.println(b+" "+c.get(m));
            }
        }
    }
    public void start(){
        while(true){
            String b = scanner.next();
            if(b.equals("-")){
                return;
            }
            a.add(b);
        }
    }
    public void stop(){
        String b = scanner.next();
        for (String anA : a) {
            if (b.equals(anA)) {
                System.out.println(anA);
                return;
            }
        }
    }
    public static void main(String[] args) {
        SetsTest test = new SetsTest();
        test.startMap();
        test.stopMap();
    }
}
