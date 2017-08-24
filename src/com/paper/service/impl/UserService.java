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
		userMapper.insertUser(uDTO);
		userMapper.updateRegUser(uDTO);
	}

	@Override
	public User_infoDTO getLoginInfo(User_infoDTO uDTO) throws Exception {
		return userMapper.getLoginInfo(uDTO);
	}

	@Override
	public int overlapEmail(User_infoDTO uDTO) throws Exception {
		return userMapper.overLapEmail(uDTO);
	}

	@Override
	public User_infoDTO getUserFindPw(User_infoDTO uDTO) throws Exception {
		User_infoDTO userDTO = null;
		userDTO = userMapper.userFindPw(uDTO);
		if(userDTO != null){
			long temp_Pw = (long)(Math.random()*9000000000l) + 1000000000l;
			String temp_password = Long.toHexString(temp_Pw);
			
			User_infoDTO tempdto = new User_infoDTO();
			tempdto.setUser_name(uDTO.getUser_name());
			tempdto.setEmail(uDTO.getEmail());
			tempdto.setPhone(uDTO.getPhone());
			tempdto.setTemp_pw(temp_password);
			
			userMapper.updateTempPw(tempdto);
			
			userDTO.setPassword(temp_password);
			return userDTO;
		}else{
			return null;
		}
	}
	
}
