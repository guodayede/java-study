package loader.two;

/**
 * @author gyc
 * @Classname User
 * @Description TODO
 * @Date 2021/5/22 12:29
 */
public class User {
    private String id;
    private String name;

    public User() {
    }

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void sout(){
        System.out.println("=======自己的加载器加载类调用方法=======");
    }
}
