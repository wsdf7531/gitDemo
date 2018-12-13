package excel;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import java.io.File;
import java.io.IOException;

/**
 * @Author: Xusj
 * @Date: 2018/12/5
 * @Description:
 */
public class PoiReadExcel {
    //

    public static void main(String[] args) {
        //需要解析的文件
        File file =new File("d://新建文件夹//12-3已改造完成开关箱.xls");
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(FileUtils.openInputStream(file));
            //指定名称页
//            HSSFSheet sheet=workbook.getSheet("Sheet1");
            //默认第一个页
            HSSFSheet sheet = workbook.getSheetAt(0);
            int firstRowNum = 0;
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 0; i <= lastRowNum; i++) {
                HSSFRow row = sheet.getRow(i);
                //获取当前行最后单元格列号
                int lastCellNum = row.getLastCellNum();
                for (int j = 0; j < lastCellNum; j++) {
                    Cell cell = row.getCell(j);
                    String value= cell.getStringCellValue();
                    System.out.println(value+ "");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
