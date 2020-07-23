import org.junit.Assert;
import org.junit.Test;

public class HumanTest {
    public HumanTest(){}
    @Test
    public void possitiveTest(){
        Human human = new Human("Daniel","Kuc",20);
        Human human1 = new Human("Damian","Kuc",30);
        Assert.assertTrue(human1.isOlder(human));
    }
    /*@Test
    public void negativeTest(){
        Human human = new Human("Daniel","Kuc",20);
        Human human1 = new Human("Damian","Kuc",30);
        Assert.assertTrue(human.isOlder(human1));
    }*/
}
