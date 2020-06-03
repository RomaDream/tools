package com.ys.tool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import junit.framework.TestCase;

public class ExcelUtilTest extends TestCase{
	
	public void test() {
		List<String> heads  = new ArrayList<>();
		heads.add("第一列-表头");
		heads.add("第二列-表头");
		heads.add("第三列-表头");
		
		List<List<String>> dataList = new ArrayList<>();
		for (int i = 0; i < 999; i++) {
			List<String> temp = new ArrayList<>();
			for(int j=1; j<=3; j++) {
				temp.add(i+"-"+j);
			}
			dataList.add(temp);
		}
		
		HSSFWorkbook hssfWorkbook = ExcelUtil.createExcelFile("test", heads, dataList);
		File file = new File("C:\\Users\\Administrator\\Desktop\\新建文件夹\\新建文件夹\\test1.xls");
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		OutputStream output = null;
		try {
			output = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			hssfWorkbook.write(output);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
