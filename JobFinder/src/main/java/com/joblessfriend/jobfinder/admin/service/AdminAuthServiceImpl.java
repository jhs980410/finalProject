package com.joblessfriend.jobfinder.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joblessfriend.jobfinder.admin.dao.AdminDao;
import com.joblessfriend.jobfinder.admin.domain.AdminVo;

@Service
public class AdminAuthServiceImpl implements AdminAuthService{
	
	@Autowired
	public AdminDao adminDao;

	@Override
	public AdminVo adminExist(String adminId, String password) {
		// TODO Auto-generated method stub
		AdminVo adminVo = adminDao.adminExist(adminId,password);
		
		if (adminVo != null) {
			return adminVo;
		}
		
		return null;
	}

	
}
