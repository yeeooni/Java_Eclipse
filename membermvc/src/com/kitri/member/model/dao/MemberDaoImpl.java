package com.kitri.member.model.dao;

import java.sql.*;
import java.util.*;

import com.kitri.member.model.*;
import com.kitri.util.DBConnection;
import com.kitri.util.DBclose;

public class MemberDaoImpl implements MemberDao{
	
//	1. private 
//	2. 전역변수
//	3. getter
	
	private static MemberDao memberDao;
	
	static {
		memberDao = new MemberDaoImpl();
	}
	
	private MemberDaoImpl( ) {}
	
	public static MemberDao getMemberDao() {
		return memberDao;
	}

	@Override
	public int idCheck(String id) {
		
		int cnt = 1;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBConnection.makeConnection();
			
			StringBuffer sql = new StringBuffer();
			
			sql.append("select count(id) \n");
			sql.append("from member \n");
			sql.append("where id = ? \n");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			rs.next();
			
			cnt = rs.getInt(1);
			
		} catch (SQLException e) {
			cnt = 1; // 오류일때 좋은 표현
			e.printStackTrace();
		} finally {
			DBclose.close(con, pstmt, rs);
		}
		
		return cnt;
	}

	@Override
	public List<ZipcodeDto> zipSearch(String doro) {
		
		List<ZipcodeDto> list = new ArrayList<ZipcodeDto>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBConnection.makeConnection();
			
			StringBuffer sql = new StringBuffer();
			
			sql.append("select 	case  \n");
			sql.append("			when length(new_post_code) = 4 then '0'||new_post_code \n");
			sql.append("			else new_post_code \n");
			sql.append("		end zipcode,  \n");
			sql.append("		sido_kor sido, gugun_kor gugun,  \n");
			sql.append("		nvl(upmyon_kor, ' ') upmyon, doro_kor doro,  \n");
			sql.append("		case when building_refer_number != '0' \n");
			sql.append("			then building_origin_number||'-'||building_refer_number  \n");
			sql.append("			else trim(to_char(building_origin_number, '99999')) \n");
			sql.append("		end building_number, sigugun_building_name \n");
			sql.append("from 	postcode \n");
			sql.append("where 	doro_kor like '%'||?||'%' \n");
			sql.append("or sigugun_building_name like '%'||?||'%' \n");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, doro);
			pstmt.setString(2, doro);
			rs = pstmt.executeQuery();
			while(rs.next()) {

				ZipcodeDto zipDto = new ZipcodeDto();
				
				zipDto.setZipcode(rs.getString("zipcode"));
				zipDto.setSido(rs.getString("sido"));
				zipDto.setGugun(rs.getString("gugun"));
				zipDto.setUpmyon(rs.getString("upmyon"));
				zipDto.setDoro(rs.getString("doro"));
				zipDto.setBuildingNumber(rs.getString("building_number"));
				zipDto.setSigugunBuildingName(rs.getString("sigugun_building_name"));
			
				list.add(zipDto);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBclose.close(con, pstmt, rs);
		}

		return list;
	}

	@Override
	public int registerMember(MemberDetailDto memberDetailDto) {
			int cnt = 0;
			Connection con = null;
			PreparedStatement pstmt = null;
			
			 try {
					con = DBConnection.makeConnection();

					StringBuffer sql = new StringBuffer();

					sql.append("insert all \n");
					sql.append("	into member (name, id, pass, emailid, emaildomain, joindate)\n");
					sql.append("	values(?, ?, ?, ?, ?, sysdate)\n");
					sql.append("	into member_detail (id, zipcode, address, address_detail, tel1, tel2, tel3)\n");
					sql.append("	values(?, ?, ?, ?, ?, ?, ?) \n");
					sql.append("select * from dual \n");
					
					pstmt = con.prepareStatement(sql.toString());

					int idx = 0;
					pstmt.setString(++idx, memberDetailDto.getName());
					pstmt.setString(++idx, memberDetailDto.getId());
					pstmt.setString(++idx, memberDetailDto.getPass());
					pstmt.setString(++idx, memberDetailDto.getEmailid());
					pstmt.setString(++idx, memberDetailDto.getEmaildomain());
					pstmt.setString(++idx, memberDetailDto.getId());
					pstmt.setString(++idx, memberDetailDto.getZipcode());
					pstmt.setString(++idx, memberDetailDto.getAddress());
					pstmt.setString(++idx, memberDetailDto.getAddressDetail());
					pstmt.setString(++idx, memberDetailDto.getTel1());
					pstmt.setString(++idx, memberDetailDto.getTel2());
					pstmt.setString(++idx, memberDetailDto.getTel3());

					cnt = pstmt.executeUpdate();
							
						} catch (SQLException e) {
							e.printStackTrace();
						} finally {
							DBclose.close(con, pstmt);
					}
			return cnt;
			
	}
	

	@Override
	public MemberDto loginMember(Map<String, String> map) {
		
		MemberDto memberDto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = DBConnection.makeConnection();
			StringBuffer sql = new StringBuffer();
			
			sql.append("select name, id, emailid, emaildomain, joindate \n");
			sql.append("from member \n");
			sql.append("where id = ? and pass = ? \n");
			
			pstmt = con.prepareStatement(sql.toString());
			
			pstmt.setString(1, map.get("userId"));
			pstmt.setString(2, map.get("userPass"));
			
			rs = pstmt.executeQuery();
			
				if(rs.next()) {
					memberDto = new MemberDto();
					
					memberDto.setName(rs.getString("name"));
					memberDto.setId(rs.getString("id"));
					memberDto.setEmailid(rs.getString("emailid"));
					memberDto.setEmaildomain(rs.getString("emaildomain"));
					memberDto.setJoindate(rs.getString("joindate"));
				}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
				DBclose.close(con, pstmt, rs);
		}
		
		return memberDto;
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
