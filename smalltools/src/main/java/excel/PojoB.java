package excel;

import java.util.Date;

/**
 * @author gyc
 * @date 2018/10/26  21:51
 */
public class PojoB {
    private String userName;
    private int age;
    private Date birthday;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
