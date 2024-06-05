package com.itwillbs.web;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itwillbs.service.MemberService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}
		)
public class MemberServiceTest {
	
	// 서비스 객체를 사용해서 서비스 기능을 테스트
	
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceTest.class);
	
	@Inject
	private MemberService mService;
	
	@Test
	public void 디비시간조회서비스_테스트() {
		logger.debug(" 시간 정보 : "+mService.getDbServerTime());
	}
	
	
}
