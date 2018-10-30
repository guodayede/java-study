package excel;

/**
 * @author gyc
 * @date 2018/10/26  21:50
 */
public class PojoA {
    @ExcelName(name = "名称")
    private String name;
    @ExcelName(name = "数量")
    private int num;
    @ExcelName(name = "价格")
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
