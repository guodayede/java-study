package excell;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
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
public class ExcelUtil {
    public static <T> void setExcel(String title,String[] columnNames, List<T> tList) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, IOException {

//        1.创建Excel工作薄对象
        HSSFWorkbook workbook=new HSSFWorkbook();
//        2.创建Excel工作表对象
        HSSFSheet sheet=workbook.createSheet(title);
//        3.创建Excel工作表的第一行
        HSSFRow row=null;
        row=sheet.createRow(0);
        for(int i=0;i<columnNames.length;i++){
            row.createCell(i).setCellValue(columnNames[i]);
        }
        for(int j=1;j<=tList.size();j++){
            T t= tList.get(j-1);
            Field[] declaredFields = t.getClass().getDeclaredFields();
            row=sheet.createRow(j);
            for(int i=0;i<declaredFields.length;i++){
                String fieldName = declaredFields[i].getName();
                String getMethodName="get"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
                Method declaredMethod = t.getClass().getDeclaredMethod(getMethodName);
                Object fieldValue = declaredMethod.invoke(t);
                if(fieldValue instanceof Integer){
                    row.createCell(i).setCellValue(Integer.valueOf(fieldValue.toString()));
                }else if(fieldValue instanceof Double){
                    row.createCell(i).setCellValue(Double.valueOf(fieldValue.toString()));
                }else if(fieldValue instanceof Date){
                    row.createCell(i).setCellValue(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(fieldValue));
                }else {
                    row.createCell(i).setCellValue(fieldValue.toString());
                }

            }
        }

        for (int i = 0; i < columnNames.length; i++) {
            sheet.autoSizeColumn(i);
            sheet.setColumnWidth(i,sheet.getColumnWidth(i)*17/10);
        }
        workbook.write(new FileOutputStream(new File("/Users/rose/IdeaProjects/java-study/smalltools/ChangeData.xls")));

        workbook.close();
    }
}
