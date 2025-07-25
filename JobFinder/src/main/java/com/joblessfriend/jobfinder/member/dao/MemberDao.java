package com.joblessfriend.jobfinder.member.dao;

import java.util.List;

import com.joblessfriend.jobfinder.company.domain.CompanyVo;
import com.joblessfriend.jobfinder.member.domain.MemberVo;

public interface MemberDao {

	MemberVo memberExist(String email, String password);

	int memberInsertOne(MemberVo memberVo);

	boolean isNicknameExists(String nickname);

	MemberVo memberEmailExist(String email);

	List<MemberVo> memberSelectList(int page);
	
	int memberCount();

	List<MemberVo> memberSelectList(int page, String keyword);

	int memberCount(String keyword);

	MemberVo memberSelectOne(int memberId);

	int memberUpdateOne(MemberVo existMemberVo);

	int memberDeleteOne(int memberId);

	int memberDeleteList(List<Integer> memberIdList);

	int updatePassword(String password, int memberId);

	MemberVo googleMemberExist(String email);

	int updateNickname(String nickname, int memberId);
	
	MemberVo selectByEmail(String email);  // email 로 memberId 찾고 memberId로 이력서 저장용



}
