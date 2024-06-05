package com.itwillbs.web;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class MysqlConnectTest {

	// DB 연결 정보
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/springdb";
	private static final String DBID = "root";
	private static final String DBPW = "1234";
	
	// DB 연결 테스트 메서드
	// @Test : jUnit을 사용해서 해당 메서드를 테스트용으로 인식하도록 설정
	//		   @Test가 있는 메서드만 실행
//	@Test
	public void test2() {
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@");
	}
	
//	@Test
//	public void testConnect() throws Exception {
//		// 1. 드라이버 로드
//		Class.forName(DRIVER);
//		
//		// 2. DB 연결
//		Connection con = DriverManager.getConnection(DBURL, DBID, DBPW);
//		
//		System.out.println(" 디비 연결 성공 ! ");
//		System.out.println(" con : "+con);

		
//		@Test
//		public void testConnect() {
//			try {
//				// 1. 드라이버 로드
//				Class.forName(DRIVER);
//				// 2. DB 연결
//				Connection con = DriverManager.getConnection(DBURL, DBID, DBPW);
//				
//				System.out.println(" 디비 연결 성공 ! ");
//				System.out.println(" con : "+con);
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally {
//				// 자원해제 - close() 실행
//			}
			
			@Test
			public void testConnect() throws Exception {
			// 1. 드라이버 로드
			Class.forName(DRIVER);
			// try - with 구문 : JDK 1.7 이후 사용 가능 
			
			// 2. DB 연결
			try (Connection con = DriverManager.getConnection(DBURL, DBID, DBPW);) {
				// => AutoCloseable 인터페이스를 상속한 객체
				
				System.out.println(" 디비 연결 성공 ! ");
				System.out.println(" con : "+con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
			}
	
}
