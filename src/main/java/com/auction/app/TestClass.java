package com.auction.app;

import java.io.File;

public class TestClass {
	public static void main(String[] args) {
		File file=new File("auction/project");
		file.mkdirs();
		System.out.println(file.getAbsolutePath());
	}
}
