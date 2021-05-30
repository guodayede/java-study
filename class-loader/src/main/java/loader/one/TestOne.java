package loader.one;

/**
 * @author gyc
 * @Classname TestOne
 * @Description TODO
 * @Date 2021/5/22 8:28
 */
public class TestOne {
    static {
        System.out.println("*****TestOne load*****");
    }

    public static void main(String[] args) {
        new A();
        System.out.println("*****TestOne main *****");
        B b=null;
    }
}

class A{
    static {
        System.out.println("*****A load*****");
    }
    public A(){
        System.out.println("*****A init*****");
    }
}

class B{
    static {
        System.out.println("*****B load*****");
    }
    public B(){
        System.out.println("*****B init*****");
    }
}