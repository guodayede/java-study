package excell;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author gyc
 * @date 2018/10/26  21:52
 */
public class Test {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, IOException {
      /*  PojoA pojoA=new PojoA();
        pojoA.setName("a");
        pojoA.setNum(1);
        pojoA.setPrice(1.1234);
        List<PojoA> lista=new ArrayList<>();
        lista.add(pojoA);
        new ExcelUtil().setExcel(new String[]{"名称","数量","价格"},lista);
*/
        PojoB pojoB=new PojoB();
        pojoB.setUserName("aa");
        pojoB.setAge(1);
        pojoB.setBirthday(new Date());

        List<PojoB> listb=new ArrayList<>();
        listb.add(pojoB);
        new ExcelUtil().setExcel("pojoB",new String[]{"名称","年龄","时间"},listb);
    }
}
