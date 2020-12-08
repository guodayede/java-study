package com.gyc.ognl;

import ognl.*;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hello
 * @Classname OgnlTest
 * @Date 2020/12/8 14:10
 * @Created by gyc
 * @Description
 */
public class OgnlTest {
    @Test
    public void ognlTest() {
        // 构建一个OgnlContext对象
        OgnlContext context = (OgnlContext) Ognl.createDefaultContext(this,
                new DefaultClassResolver(),
                new DefaultTypeConverter(),
                new DefaultMemberAccess(true));

        Map<String,Object> hashMap=new HashMap<>(1);
        hashMap.put("name",6);
        context.setRoot(hashMap);
        String expression="name=='1'";
        try {
            Boolean flag = (Boolean) Ognl.getValue(expression, context, context.getRoot());
            System.out.println(flag);
        } catch (OgnlException e) {
            e.printStackTrace();
        }

    }
}
