//package com.sdx.lx.service.sample;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFCellStyle;
//import org.apache.poi.hssf.usermodel.HSSFFont;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.hssf.util.HSSFColor;
//
//public class Test {
//
//	public static void main(String[] args) {
//		 HSSFWorkbook workbook = null;
//		    workbook = new HSSFWorkbook();
//		    //获取参数个数作为excel列数
//		    int columeCount = 6;
//		    //获取List size作为excel行数
//		    int rowCount = 20;
//		    HSSFSheet sheet = workbook.createSheet("sheet name");
//		    //创建第一栏
//		    HSSFRow headRow = sheet.createRow(0);
//		    String[] titleArray = {"id", "name", "age", "email", "address", "phone"};
//		    for(int m=0;m<=columeCount-1;m++)
//		    {
//		        HSSFCell cell = headRow.createCell(m);
//		        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
//		        sheet.setColumnWidth(m, 6000);
//		        HSSFCellStyle style = workbook.createCellStyle();
//		        HSSFFont font = workbook.createFont();
//		        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//		        short color = HSSFColor.RED.index;
//		        font.setColor(color);
//		        style.setFont(font);
//		        //填写数据
//		        cell.setCellStyle(style);
//		        cell.setCellValue(titleArray[m]);
//
//		    }
//		    int index = 0;
//		    //写入数据
//		    for(RowEntity entity : pRowEntityList)
//		    {
//		        //logger.info("写入一行");
//		        HSSFRow row = sheet.createRow(index+1);
//		        for(int n=0;n<=columeCount-1;n++)
//		            row.createCell(n);
//		        row.getCell(0).setCellValue(entity.getId());
//		        row.getCell(1).setCellValue(entity.getName());
//		        row.getCell(2).setCellValue(entity.getAge());
//		        row.getCell(3).setCellValue(entity.getEmail());
//		        row.getCell(4).setCellValue(entity.getAddress());
//		        row.getCell(5).setCellValue(entity.getPhone());
//		        index++;
//		    }
//
//		    //写到磁盘上
//		    try {
//		        FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
//		        workbook.write(fileOutputStream);
//		        fileOutputStream.close();
//		    } catch (FileNotFoundException e) {
//		        e.printStackTrace();
//		    } catch (IOException e) {
//		        e.printStackTrace();
//		    }
//
//	}
//
//}
