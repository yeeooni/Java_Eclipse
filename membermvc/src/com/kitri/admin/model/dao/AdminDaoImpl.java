package com.kitri.admin.model.dao;

import java.sql.*;
import java.util.*;

import com.kitri.member.model.MemberDetailDto;
import com.kitri.member.model.MemberDto;
import com.kitri.util.DBConnection;
import com.kitri.util.DBclose;

public class AdminDaoImpl  implements AdminDAO{
	
private static AdminDAO adminDao;
	
	static {
		adminDao = new AdminDaoImpl();
	}
	
	private AdminDaoImpl () {}

	public static AdminDAO getAdminDao() {
		return adminDao;
	}
	
	@Override
	public List<MemberDetailDto> getMemberList(Map<String, String> map) {
		List<MemberDetailDto> list = new ArrayList<MemberDetailDto>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = DBConnection.makeConnection();
			StringBuffer sql = new StringBuffer();
			
			sql.append("select m.name, m.id, m.emailid, m.emaildomain, m.joindate, \n");
			sql.append("	d.tel1, d.tel2, d.tel3, d.zipcode, d.address, d.address_detail \n");
			sql.append("from member m, member_detail d \n");
			sql.append("where m.id = d.id \n");
			
			String key = map.get("key");
			String word = map.get("word");
			
				if(word != null && !word.isEmpty()) {
					if("id".equals(key)) {
						sql.append("and m.id = ? \n");
					} else {
						sql.append("where d." + key + " like '%'||?||'%' \n");
					}
				}
			
			pstmt = con.prepareStatement(sql.toString());
			
			if(word != null && !word.isEmpty()) {
				pstmt.setString(1, word);
			}
			
			rs = pstmt.executeQuery();
			
				while(rs.next()) {
					MemberDetailDto  memberDetailDto = new MemberDetailDto();
					
					memberDetailDto.setName(rs.getString("name"));
					memberDetailDto.setId(rs.getString("id"));
					memberDetailDto.setEmailid(rs.getString("emailid"));
					memberDetailDto.setEmaildomain(rs.getString("emaildomain"));
					memberDetailDto.setJoindate(rs.getString("joindate"));
					memberDetailDto.setTel1(rs.getString("tel1"));
					memberDetailDto.setTel2(rs.getString("tel2"));
					memberDetailDto.setTel3(rs.getString("tel3"));
					memberDetailDto.setZipcode(rs.getString("zipcode"));
					memberDetailDto.setAddress(rs.getString("address"));
					memberDetailDto.setAddressDetail(rs.getString("address_detail"));
	
					list.add(memberDetailDto);
				}
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
				DBclose.close(con, pstmt, rs);
		}
//		System.out.println("!!!! : "+list);
		return list;
	}
}
