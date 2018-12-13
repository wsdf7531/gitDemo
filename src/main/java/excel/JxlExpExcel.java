package excel;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import java.io.File;


/**
 * @Author: Xusj
 * @Date: 2018/10/31
 * @Description:
 */
public class JxlExpExcel {

    public static void main(String[] args) {
        //创建excel文件
        File file=new File("d://jxl.xls");
        String[] title={"name","age","sex"};
        try {
            file.createNewFile();
            //创建workBook
            WritableWorkbook workbook= Workbook.createWorkbook(file);
            //创建sheet
            WritableSheet sheet=workbook.createSheet("sheet1",0);
            Label label;
            for (int i=0;i<title.length;i++){
                label=new Label(i,0,title[i]);
                sheet.addCell(label);
            }
            //添加数据
            for (int i=1;i<10;i++){
                label=new Label(0,i,"a"+i);
                sheet.addCell(label);
                String age= String.valueOf(10+i);
                label=new Label(1,i,age);
                sheet.addCell(label);
                label=new Label(2,i,"女");
                sheet.addCell(label);
            }
            workbook.write();
            workbook.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
