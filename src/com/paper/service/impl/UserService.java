package com.paper.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paper.dto.User_infoDTO;
import com.paper.persistance.mapper.UserMapper;
import com.paper.service.IUserService;

@Service("UserService")
public class UserService implements IUserService {
	@Resource(name="UserMapper")
	private UserMapper userMapper;

	@Override
	public List<User_infoDTO> getUserList() throws Exception {
		return userMapper.getUserList();
	}

	@Override
	public void insertUser(User_infoDTO uDTO) throws Exception {
		userMapper.insertUser();
	}

	@Override
	public User_infoDTO getLoginInfo(User_infoDTO uDTO) throws Exception {
		return userMapper.getLoginInfo(uDTO);
	}

	@Override
	public int overlapEmail(User_infoDTO uDTO) throws Exception {
		return userMapper.overLapEmail(uDTO);
	}
	
}
