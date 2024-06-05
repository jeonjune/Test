package com.itwillbs.web;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@RunWith(SpringJUnit4ClassRunner.class)
// => 스프링 jUnit을 사용해서 테스트 하겠다
//@ContextConfiguration(
//		locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}
//		)
// => 스프링의 설정파일을 불러온다 (root-context.xml과 연결)

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}
		)
public class DataSourceTest {

	// 디비연결 정보 -> root-context.xml 에 생성된 정보를 가져오기
//	@Inject
	// => 의존관계 주입 (DI)
	@Inject
	private DataSource ds;
	
	@Inject
	private SqlSessionFactory factory;
	
	@Inject
	private SqlSession sqlSession;
	
	//@Test
	public void test() {
		System.out.println("ds : "+ds);
	}
	
	// 디비연결 테스트
//	@Test
	public void connectTest() {
		try {
			Connection con = ds.getConnection();
			System.out.println(" 디비 연결 성공! ");
			System.out.println(" con : "+con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void factoryTest() {
		SqlSession session = factory.openSession();
		
		System.out.println(session);
		
	}
}
