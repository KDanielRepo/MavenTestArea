import org.junit.Assert;
import org.junit.Test;

public class Human {
    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    public boolean isOlder(Human human){
        return this.getAge()>human.getAge();
    }

    private String surname;
    private Integer age;
    public Human(String name,String surname,Integer age){
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }
        if(obj instanceof Human){
            Human human2 = (Human) obj;
            return name.equals(((Human) obj).name)&&surname.equals(((Human) obj).surname)&&age==((Human) obj).age;
        }
        return false;
    }


    @Override
    public int hashCode() {
        return 13*name.hashCode()+7*surname.hashCode()+3*age.hashCode();
    }
}
