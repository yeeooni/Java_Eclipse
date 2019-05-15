package com.kitri.member.model.service;

import com.kitri.member.model.*;

public interface MemberService {

	//int 0 이면 사용할 수 있다. 1이면 사용할 수 없다.
	String idCheck(String id);
	
	//zipcodeDto list
	String zipSearch(String zipcode);
	
	//
	int registerMember(MemberDetailDto memberDetailDto);
	
	MemberDto loginMember(String id, String pass);
	
	//id를 가져오면 return memberDetailDto
	MemberDetailDto getMember(String id);
	
	//memberDetailDto
	int modifyMember(MemberDetailDto memeDetailDto);
	
	//id 
	int deleteMember(String id);
	
}
