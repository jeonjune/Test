package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.MemberVO;

public interface MemberService {
	// 컨트롤러에서 처리를 수행하는 추상메서드 작성
	// 실제 동작이 유추가능한 이름 지정
	
	// DB 시간 정보 조회
//	public String getTime();
	public String getDbServerTime();
	
	// 회원가입
	public void memberJoin(MemberVO vo);
	
	// 로그인 체크
	public MemberVO memberLogin(MemberVO loginVO);
	
	// 회원정보 체크
	public MemberVO memberInfo(String id);
	
	// 회원정보 수정
	public void memberUpdate(MemberVO updateVO);
	
	// 회원정보 삭제
	public int memberDelete(MemberVO deleteVO);
	
	// 회원목록 조회
	public List<MemberVO> memberList();
}
