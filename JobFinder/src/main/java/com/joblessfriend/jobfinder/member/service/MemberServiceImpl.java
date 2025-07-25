package com.joblessfriend.jobfinder.member.service;

import java.security.SecureRandom;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.joblessfriend.jobfinder.auth.controller.AuthController;
import com.joblessfriend.jobfinder.company.domain.CompanyVo;
import com.joblessfriend.jobfinder.member.dao.MemberDao;
import com.joblessfriend.jobfinder.member.domain.MemberVo;

@Service
public class MemberServiceImpl implements MemberService {

	private Logger logger = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private MemberDao memberDao;

	private final PasswordEncoder passwordEncoder;

	public MemberServiceImpl(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	// 로그인
	@Override
	public MemberVo memberExist(String email, String password) {
		// TODO Auto-generated method stub

		MemberVo memberVo = memberDao.memberEmailExist(email);

		if (memberVo != null) {
			// 암호 비교: 비밀번호가 평문인 경우
			if (password.equals(memberVo.getPassword())) {
				return memberVo;
			}
			// 암호가 암호화된 경우, passwordEncoder 사용
			else if (passwordEncoder.matches(password, memberVo.getPassword())) {
				return memberVo;
			}
		}

		// 회원이 존재하지 않거나 비밀번호가 틀린 경우 null 반환
		return null;
	}

	// 닉네임 생성 메소드
	@Override
	public String generateUniqueNickname() {
		final String CHAR_POOL = "abcdefghijklmnopqrstuvwxyz0123456789";
		final int LENGTH = 5;
		SecureRandom random = new SecureRandom();
		String nickname;

		do {
			StringBuilder sb = new StringBuilder("user0");
			for (int i = 0; i < LENGTH; i++) {
				int index = random.nextInt(CHAR_POOL.length());
				sb.append(CHAR_POOL.charAt(index));
			}
			nickname = sb.toString();
		} while (memberDao.isNicknameExists(nickname)); // 중복 확인

		return nickname;
	}

	// 회원가입
	@Override
	public int memberInsertOne(String email, String password) {

		MemberVo memberVo = new MemberVo();

		memberVo.setEmail(email);
		memberVo.setNickname(generateUniqueNickname());

		// 비밀번호 암호화
		String pwdEncoder = passwordEncoder.encode(password);
		System.out.println("비번 확인: " + password + " / " + pwdEncoder);
		memberVo.setPassword(pwdEncoder);

		return memberDao.memberInsertOne(memberVo);
	}

	// 이메일 중복확인
	@Override
	public MemberVo memberEmailExist(String email) {
		// TODO Auto-generated method stub
		return memberDao.memberEmailExist(email);
	}

	@Override // 관리자.이용자 겸용(분할해야함)
	public int memberDeleteOne(int memberId) {
		// TODO Auto-generated method stub
		return memberDao.memberDeleteOne(memberId);
	}

	//마이페이지 비밀번호 변경
	@Override // 회원용
	public int updatePassword(String password, int memberId) {
		// TODO Auto-generated method stub
		//비밀번호 암호화
		String pwdEncoder = passwordEncoder.encode(password);
		System.out.println("비번 확인: " + password + " / " + pwdEncoder);
				
		return memberDao.updatePassword(pwdEncoder, memberId);
	}
	
	// 닉네임 변경
	@Override
	public int updateNickname(String nickname, int memberId) {
		// TODO Auto-generated method stub
		return memberDao.updateNickname(nickname, memberId);
	}

}
