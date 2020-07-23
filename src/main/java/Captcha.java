import java.io.*;
import java.util.concurrent.ThreadLocalRandom;

public class Captcha implements Serializable {
    private String a;
    private String b;
    private static final long serialVersionUID = 98671273l;

    public boolean compare(String a, String b){
        boolean equal = false;
        if(a.equals(b)){
            equal = true;
        }
        return equal;
    }
    public String generate(){
        String a = "";
        String[] c = new String[]{"a","X","f","G","4","1","K","L"};
        for (int i = 0; i < 5; i++) {
            int b = ThreadLocalRandom.current().nextInt(0,8);
            a = a+c[b];
        }
        return a;
    }
    public void serialize(){
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("captcha.bin"))) {
            output.writeObject(this);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void deSerialize(){
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("captcha.bin"))) {
            Captcha captcha = (Captcha) input.readObject();
            System.out.println(captcha.getA());
            System.out.println(captcha.getB());
        } catch (FileNotFoundException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }
}
