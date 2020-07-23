import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test4 {
    int a;
    public Test4(Class aClass) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        //Test2 test2 = new Test2();
        Field field = aClass.getDeclaredField("test3");
        field.setAccessible(true);
        Object value = field.get(aClass.getDeclaredConstructor().newInstance());
        Object value2 = aClass.getDeclaredConstructor().newInstance();
        Method method2 = value2.getClass().getDeclaredMethod("test",new Class[]{});
        Method method = value.getClass().getDeclaredMethod("testing",new Class[]{});
        a = (int)method.invoke(value);
        method2.invoke(value2);
        //System.out.println(field.toString());
    }
    public int getA(){
        return a;
    }
}
