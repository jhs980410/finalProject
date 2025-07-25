package com.joblessfriend.jobfinder.company.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.joblessfriend.jobfinder.company.domain.CompanyVo;

@Repository
public class CompanyDaoImpl implements CompanyDao{
	
	@Autowired
	private SqlSession sqlSession;
	
	private String namespace = "com.joblessfriend.jobfinder.company.";
	




	@Override
	public CompanyVo companySelectOne(int companyId) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace + "companySelectOne",companyId);
	}


	@Override
	public int companyUpdateOne(CompanyVo existCompanyVo) {
		// TODO Auto-generated method stub
		return sqlSession.update(namespace + "companyUpdateOne",existCompanyVo);
		
	}



	@Override
	public int companyDeleteOne(int companyId) {
		// TODO Auto-generated method stub
		return sqlSession.delete(namespace + "companyDeleteOne", companyId);
	}



	
	

	// 기업회원가입
	@Override
	public int companyInsertOne(CompanyVo companyVo) {
		// TODO Auto-generated method stub
		return sqlSession.insert(namespace + "companyInsertOne", companyVo);
	}

	// 기업로그인
	@Override
	public CompanyVo companyExist(String email, String password) {
		// TODO Auto-generated method stub
		HashMap<String, String> paramMap = new HashMap<>();
		
		paramMap.put("email", email);
		paramMap.put("password", password);
		
		return sqlSession.selectOne(namespace + "companyExist", paramMap);
	}

	// 아이디 찾기
	@Override
	public CompanyVo companyFindId(String representative, String brn) {
		// TODO Auto-generated method stub
		HashMap<String, String> paramMap = new HashMap<>();
		
		paramMap.put("representative", representative);
		paramMap.put("brn", brn);
		
		return sqlSession.selectOne(namespace + "companyFindId", paramMap);
	}

	// 이메일 중복확인
	@Override
	public CompanyVo companyEmailExist(String email) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace + "companyEmailExist", email);
	}

	// 비밀번호 변경
	@Override
	public int updatePassword(String password, int companyId) {
		// TODO Auto-generated method stub
		HashMap<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("companyId", companyId);
		paramMap.put("password", password);
		
		return sqlSession.update(namespace + "updatePassword", paramMap);
	}

}
