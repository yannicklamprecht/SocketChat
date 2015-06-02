import java.io.Serializable;

/**
 * Created by yannick on 02.06.2015.
 */
public class Student implements Serializable {

    private int id;
    private String name;


    public Student(int id, String name){
        this.id=id;
        this.name=name;
    }

    public String getName(){
        return name;
    }
    public int getId(){
        return id;
    }

}
