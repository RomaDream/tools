package com.ys.java8;

import java.io.File;

public class Charter01 {
	public static void main(String[] args) {
		File[] hiddenFiles = new File(".").listFiles(File :: isFile);
		System.out.println(hiddenFiles.length);
	}
}
