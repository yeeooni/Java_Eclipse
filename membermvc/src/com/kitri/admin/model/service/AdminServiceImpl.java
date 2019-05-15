package com.kitri.admin.model.service;

import java.util.*;

import com.kitri.admin.model.dao.AdminDaoImpl;
import com.kitri.member.model.MemberDetailDto;

public class AdminServiceImpl implements AdminService{

	private static AdminService adminService;
	
	static {
		adminService = new AdminServiceImpl();
	}
	
	private AdminServiceImpl () {}

	public static AdminService getAdminService() {
		return adminService;
	}

	@Override
	public String getMemberList(String key, String word) {
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("key", key);
		map.put("word", word);
		
		List<MemberDetailDto> list = AdminDaoImpl.getAdminDao().getMemberList(map);
		
		String result = "";
		
		result += "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n";
		result += "<memberlist>\n";
			for(MemberDetailDto memberDetailDto : list) {
				result += "	<member>\n";
				result += "		<id>" + memberDetailDto.getId() +"</id>\n";
				result += "		<name>" + memberDetailDto.getName() +"</name>\n";
				result += "		<email>" + memberDetailDto.getEmailid() + "@" + memberDetailDto.getEmaildomain() +"</email>";
				result += "		<tel>" + memberDetailDto.getTel1() + "-" + memberDetailDto.getTel2() + "-" + memberDetailDto.getTel3() + "</tel>\n";
				result += "		<address><![CDATA[" + memberDetailDto.getZipcode() + " " + memberDetailDto.getAddress() + "]]></address>\n";
				result += "		<joindate>" + memberDetailDto.getJoindate() +"</joindate>\n";
				result += "	</member>\n";
			}
		result += "</memberlist>\n";
	
		
		return result;
		
	}
	
}
