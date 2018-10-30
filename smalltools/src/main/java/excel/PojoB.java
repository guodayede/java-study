package excel;

import java.util.Date;

/**
 * @author gyc
 * @date 2018/10/26  21:51
 */
public class PojoB {
    @ExcelName(name = "姓名")
    private String userName;
    @ExcelName(name = "年龄")
    private int age;
    @ExcelName(name = "生日")
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
