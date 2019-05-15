package com.kitri.member.model.service;

import java.util.*;

import com.kitri.member.model.*;
import com.kitri.member.model.dao.MemberDaoImpl;

public class MemberServiceImpl implements MemberService {

	// 싱글톤 : 객체를 하나만 만드는 개발 방법
	private static MemberService memberService;

	static {
		memberService = new MemberServiceImpl();
	}

	private MemberServiceImpl() {

	}

	public static MemberService getMemberService() {
		return memberService;
	}

	@Override
	public String idCheck(String id) {
		int cnt = MemberDaoImpl.getMemberDao().idCheck(id);
//		System.out.println("cnt == " + cnt);

		String result = "";
		result += "<idcount>";
		result += "	<cnt>" + cnt + "</cnt>";
		result += "</idcount>";

		return result;
	}

	@Override
	public String zipSearch(String doro) {

		String result = "";
		List<ZipcodeDto> list = MemberDaoImpl.getMemberDao().zipSearch(doro);

		result += "<ziplist>";
		for (ZipcodeDto zipDto : list) {
			result += "	<zip> \n";
			result += "		<zipcode>" + zipDto.getZipcode() + "</zipcode> \n";
			result += "		<address><![CDATA[" + zipDto.getSido() + " " + zipDto.getGugun() + " " + zipDto.getUpmyon() + " "
					+ zipDto.getDoro() + " " + zipDto.getBuildingNumber() + " " + zipDto.getSigugunBuildingName()
					+ "]]></address> \n";
			result += "	</zip> \n";
		}
		result += "</ziplist> \n";
//		System.out.println(result);
		return result;
	}

	@Override
	public int registerMember(MemberDetailDto memberDetailDto) {

		return MemberDaoImpl.getMemberDao().registerMember(memberDetailDto);
	}

	@Override
	public MemberDto loginMember(String id, String pass) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", id);
		map.put("userPass", pass);
		return MemberDaoImpl.getMemberDao().loginMember(map);
	}

	@Override
	public MemberDetailDto getMember(String id) {

		return null;
	}

	@Override
	public int modifyMember(MemberDetailDto memeDetailDto) {

		return 0;
	}

	@Override
	public int deleteMember(String id) {

		return 0;
	}

}
