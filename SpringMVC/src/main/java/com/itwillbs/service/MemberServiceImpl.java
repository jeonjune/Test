package com.itwillbs.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.persistence.MemberDAO;

/**
 *	서비스는 컨트롤러와 DAO를 연결
 *	=> DAO를 호출
 */
// @Service : 해당 객체를 Service 객체(bean)로 root-context.xml이 인식하도록 설정
@Service
public class MemberServiceImpl implements MemberService {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	// DAO를 호출하기(객체를 생성) => 의존 객체 주입
	// MemberDAO mdao = new MemberDAOImpl(); (직접 생성 X)
	// root-context.xml에 생성되어있는 DAO객체(빈)을 주입
	
	@Inject
	private MemberDAO mdao;

	@Override
	public String getDbServerTime() {
		return mdao.getTime();
	}

	@Override
	public void memberJoin(MemberVO vo) {
		// 컨트롤러 -> 서비스 
		logger.debug(" memberJoin(MemberVO vo) 실행 ");
		
		// DAO 메서드 호출
		mdao.insertMember(vo);
		
		logger.debug(" ╰(*°▽°*)╯ 회원가입 성공 ! ╰(*°▽°*)╯ ");
	}

	@Override
	public MemberVO memberLogin(MemberVO loginVO) {
		logger.debug(" 컨트롤러 -> 서비스 호출 ");
		logger.debug(" memberLogin(MemberVO loginVO) 실행");
		
		logger.debug(" 서비스 -> DAO 호출 ");
		// DAO 로그인 처리 동작 호출
		MemberVO resultVO = mdao.loginMember(loginVO);
		logger.debug(" DAO 처리 결과 -> 서비스 -> 컨트롤러 ");
		
		return resultVO;
	}

	@Override
	public MemberVO memberInfo(String id) {
		logger.debug(" 컨트롤러 -> 서비스 호출 ");
		logger.debug(" memberInfo(String id) 실행");
		
		logger.debug(" 서비스 -> DAO 호출 ");
		// DAO 회원정보 조회 처리 동작 호출
		MemberVO resultVO = mdao.getMember(id);
		logger.debug(" DAO 처리 결과 -> 서비스 -> 컨트롤러 ");
		
		return resultVO;
	}

	@Override
	public void memberUpdate(MemberVO updateVO) {
		logger.debug(" 컨트롤러 -> 서비스 호출 ");
		logger.debug(" memberUpdate(MemberVO updateVO) 실행");
		
		mdao.updateMember(updateVO);
		
	}

	@Override
	public int memberDelete(MemberVO deleteVO) {
		logger.debug(" 컨트롤러 -> 서비스 호출 ");
		logger.debug(" memberDelete(MemberVO deleteVO) 실행");
		
		return mdao.deleteMember(deleteVO);
		
		
	}

	@Override
	public List<MemberVO> memberList() {
		logger.debug(" 컨트롤러 -> 서비스 호출 ");
		logger.debug(" memberList() 실행");
		
		return mdao.memberList();
	}
	
	
	
	

	
	
	
	
	
	
}
