package excell;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author gyc
 * @date 2018/10/26  21:45
 */
public class ExcelUtil<T> {

    public HSSFWorkbook setExcel(String title,String[] columnNames, List<T> tList) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, IOException {
//        1.创建Excel工作薄对象
        HSSFWorkbook workbook=new HSSFWorkbook();
//        2.创建Excel工作表对象
        HSSFSheet sheet=workbook.createSheet(title);
        HSSFRow row=null;
//        3.创建Excel工作表的第一行，并填充列名
        row=sheet.createRow(0);
        for(int i=0;i<columnNames.length;i++){
            row.createCell(i).setCellValue(columnNames[i]);
        }
        Field[] declaredFields = tList.get(0).getClass().getDeclaredFields();
//        4.将数据填充至表格中
        for(int j=1;j<=tList.size();j++){
            T t= tList.get(j-1);
            row=sheet.createRow(j);
            for(int i=0;i<declaredFields.length;i++){
                // 通过反射获取属性值
                String fieldName = declaredFields[i].getName();
                String getMethodName="get"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
                Method declaredMethod = t.getClass().getDeclaredMethod(getMethodName);
                //执行方法
                Object fieldValue = declaredMethod.invoke(t);
                //判断是否为空
                if(fieldValue!=null &&  !"".equals(fieldValue)){
                    //判断属性值类型
                    if(fieldValue instanceof Integer){
                        row.createCell(i).setCellValue(Integer.valueOf(fieldValue.toString()));
                    }else if(fieldValue instanceof Double){
                        row.createCell(i).setCellValue(Double.valueOf(fieldValue.toString()));
                    }else if(fieldValue instanceof Date){
                        row.createCell(i).setCellValue(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(fieldValue));
                    }else {
                        row.createCell(i).setCellValue(fieldValue.toString());
                    }
                }else {
                    row.createCell(i).setCellValue("");
                }

            }
        }
//       自动设置列宽
        for (int i = 0; i < columnNames.length; i++) {
            sheet.autoSizeColumn(i);
//            sheet.setColumnWidth(i,sheet.getColumnWidth(i)*17/10);
        }

        return workbook;

    }
}
