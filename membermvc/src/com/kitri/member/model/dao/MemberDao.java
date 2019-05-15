package com.kitri.member.model.dao;

import java.util.List;
import java.util.Map;

import com.kitri.member.model.*;

public interface MemberDao {
	
	int idCheck(String id);
	
	//zipcodeDto list
	List<ZipcodeDto> zipSearch(String zipcode);
	//
	int registerMember(MemberDetailDto memberDetailDto);
	
	MemberDto loginMember(Map<String, String> map);
	
	//id를 가져오면 return memberDetailDto
	MemberDetailDto getMember(String id);
	
	//memberDetailDto
	int modifyMember(MemberDetailDto memeDetailDto);
	
	//id 
	int deleteMember(String id);
}
