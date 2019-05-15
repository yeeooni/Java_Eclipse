package com.kitri.jdbctest;

public class DriverLoadingTest {

	//생성자 :: 최초에 한번만 실행
	public DriverLoadingTest() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로딩 성공!!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void main(String[] args) {
		new DriverLoadingTest();
	}
	
}
