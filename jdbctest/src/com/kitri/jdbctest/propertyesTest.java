package com.kitri.jdbctest;

import java.io.*;
import java.util.Properties;

public class propertyesTest {
public static void main(String[] args) {
	Properties pro = new Properties();
	try {
		pro.load(new FileReader(new File("src\\com"
				+ "\\kitri\\jdbctest\\test.properties")));
		String name = pro.getProperty("name_cn");
		System.out.println(name);
	} catch (FileNotFoundException e) {
		
		e.printStackTrace();
	} catch (IOException e) {
		
		e.printStackTrace();
	}
}
}
