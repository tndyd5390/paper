package com.paper.service;

import java.util.List;

import com.paper.dto.User_infoDTO;

public interface IUserService {
	public List<User_infoDTO> getUserList() throws Exception;

	public void insertUser(User_infoDTO uDTO) throws Exception;

	public User_infoDTO getLoginInfo(User_infoDTO uDTO) throws Exception;

	public int overlapEmail(User_infoDTO uDTO) throws Exception;

	public User_infoDTO getUserFindPw(User_infoDTO uDTO) throws Exception;
	
	public boolean deleteUserAllChecked(User_infoDTO uDTO) throws Exception;

	public List<User_infoDTO> userSearch(User_infoDTO uDTO) throws Exception;
}
