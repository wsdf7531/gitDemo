package excel;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import java.io.File;


/**
 * @Author: Xusj
 * @Date: 2018/10/31
 * @Description:
 */
public class JxlReadExcel {
    /**
     * Jxl读取解析
     */
    public static void main(String[] args) {
        //读取文件
        try {
            //创建workBook
            Workbook workbook=Workbook.getWorkbook(new File("d://新建文件夹//12-3已改造完成开关箱.xls"));
            //获取sheet
            Sheet sheet=workbook.getSheet(0);
            for (int i=0;i<sheet.getRows();i++){
                for (int j=0;j<sheet.getColumns();j++){
                    Cell cell=sheet.getCell(j,i);
                    System.out.print(cell.getContents()+" ");
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
