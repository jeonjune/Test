package com.itwillbs.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Data : 자동으로 get/set 메서드, toString 메서드 사용

@Data
//@Getter
//@Setter
//@ToString
//@AllArgsConstructor
//@NoArgsConstructor
public class MemberVO {
	// VO(Value Object) == DTO
//	private int aS; #{aS} => setaS() X /setAS() O
	
	private String userid;
	private String userpw;
	private String username;
	private String useremail;
	
	private Timestamp regdate;
	private Timestamp updatedate;
	
	// alt + shift + s -> r (get/set 메서드)
	
	
	// alt + shift + s -> s

	
	
}
