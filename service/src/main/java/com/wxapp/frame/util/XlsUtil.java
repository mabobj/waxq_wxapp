package com.wxapp.frame.util;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * xls工具类
 *
 * @author hjn
 */
public class XlsUtil {

    public static LinkedList<String> read(String filePath) throws IOException {
        LinkedList<String> list = new LinkedList<>();

        String fileType = filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length());
        InputStream stream = new FileInputStream(filePath);
        Workbook wb = null;
        if (fileType.equals("xls")) {
            wb = new HSSFWorkbook(stream);
        } else if (fileType.equals("xlsx")) {
            wb = new XSSFWorkbook(stream);
        } else {
            System.out.println("您输入的excel格式不正确");
        }
        Sheet sheet1 = wb.getSheetAt(0);
        for (Row row : sheet1) {
            String c = "";
            for (Cell cell : row) {
                DecimalFormat df = new DecimalFormat("#");
                String mobile = cell.toString();
                switch (cell.getCellType()) {
                    case HSSFCell.CELL_TYPE_NUMERIC:// 数字
                        mobile = df.format(cell.getNumericCellValue());
                        break;
                }
                c = c + mobile.replace("\"", "").trim() + "\t";
            }
            if (c.trim().length() > 0) {
                list.add(c);
            }
        }

        return list;
    }

    public static boolean write(String outPath, List<String[]> dataList) throws Exception {
        String fileType = outPath.substring(outPath.lastIndexOf(".") + 1, outPath.length());
        System.out.println(fileType);
        // 创建工作文档对象
        Workbook wb = null;
        if (fileType.equals("xls")) {
            wb = new HSSFWorkbook();
        } else if (fileType.equals("xlsx")) {
            wb = new XSSFWorkbook();
        } else {
            System.out.println("您的文档格式不正确！");
            return false;
        }
        // 创建sheet对象
        Sheet sheet1 = (Sheet) wb.createSheet("sheet1");
        /*// 循环写入行数据
        for (int i = 0; i < 5; i++) {
            Row row = (Row) sheet1.createRow(i);
            // 循环写入列数据
            for (int j = 0; j < 8; j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue("测试" + j);
            }
        }*/
        for (int i = 0; i < dataList.size(); i++) {
            Row row = (Row) sheet1.createRow(i);
            String[] col = dataList.get(i);
            for (int j = 0; j < col.length; j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(col[j]);
            }
        }

        // 创建文件流
        OutputStream stream = new FileOutputStream(outPath);
        // 写入数据
        wb.write(stream);
        // 关闭文件流
        stream.close();
        return true;
    }


    public static void main(String[] args) {
        try {
            //XlsUtil.write("D:" + File.separator + "out.xlsx");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            XlsUtil.read("D:" + File.separator + "out.xlsx");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
