package excel;

import com.sun.org.apache.xml.internal.resolver.helpers.FileURL;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author: Xusj
 * @Date: 2018/10/31
 * @Description:
 */
public class PoiExpExcel {

    public static void main(String[] args) {
        String[] title={"name","age","sex"};
        //创建excel工作簿
        HSSFWorkbook workbook=new HSSFWorkbook();
        //创建一个工作表sheet
        HSSFSheet sheet=workbook.createSheet();
        //创建第一行
        HSSFRow row=sheet.createRow(0);
        HSSFCell cell;
        //插入第一行数据 name/age/sex
        for (int i=0;i<title.length;i++){
            cell=row.createCell(i);
            cell.setCellValue(title[i]);
        }
        //追加数据
        for (int i=1;i<10;i++){
            HSSFRow nextRow=sheet.createRow(i);
            HSSFCell nextCell=nextRow.createCell(0);
            nextCell.setCellValue("a"+i);
            nextCell=nextRow.createCell(1);
            nextCell.setCellValue("1"+i);
            nextCell=nextRow.createCell(2);
            nextCell.setCellValue("男");
        }
        //创建excel
        File file=new File("d://poi.xls");
        try {
            file.createNewFile();
            FileOutputStream stream= FileUtils.openOutputStream(file);
            workbook.write(stream);
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
