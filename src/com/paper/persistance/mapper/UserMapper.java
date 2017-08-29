package com.paper.persistance.mapper;

import java.util.List;

import com.paper.config.Mapper;
import com.paper.dto.User_infoDTO;

@Mapper("UserMapper")
public interface UserMapper {
	public User_infoDTO getLoginInfo(User_infoDTO uDTO) throws Exception;

	public List<User_infoDTO> getUserList() throws Exception;

	public void insertUser(User_infoDTO uDTO) throws Exception;

	public int overLapEmail(User_infoDTO uDTO) throws Exception;

	public void updateRegUser(User_infoDTO uDTO) throws Exception;

	public User_infoDTO userFindPw(User_infoDTO uDTO) throws Exception;

	public void updateTempPw(User_infoDTO tempdto) throws Exception;

	public int deleteUserAllChecked(User_infoDTO uDTO) throws Exception;

	public List<User_infoDTO> userSearch(User_infoDTO uDTO) throws Exception;
}
