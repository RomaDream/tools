package com.ys.tool;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.hutool.core.text.csv.CsvData;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvRow;
import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.core.text.csv.CsvWriter;
import cn.hutool.core.util.CharsetUtil;

public class CSVUtil {
	public static void main(String[] args) {
		wirter();
	}
	
	public static void reader() {
		CsvReader reader = CsvUtil.getReader();
		CsvData csvData = reader.read(new File("C:\\Users\\Administrator\\Desktop\\新建文本文档.csv"));
		List<CsvRow> rows = csvData.getRows();
		for (CsvRow csvRow : rows) {
			System.out.println(csvRow.getRawList());
		}
	}
	
	public static void wirter() {
		CsvWriter writer = CsvUtil.getWriter("C:\\Users\\Administrator\\Desktop\\a.csv", CharsetUtil.CHARSET_UTF_8);
		writer.write(
					data()
				);
	}
	
	public static List<String[]> data(){
		List<String[]> list = new ArrayList<>();
		list.add(new String[] {"aaaa", "bbbb", "cccc", "ddd,d"});
		list.add(new String[] {"wwww", "rrrr", "4444", "55555"});
		return list;
	}
}
