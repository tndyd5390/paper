package com.paper.persistance.mapper;

import java.util.List;

import com.paper.config.Mapper;
import com.paper.dto.User_infoDTO;

@Mapper("UserMapper")
public interface UserMapper {
	public List<User_infoDTO> getUserList() throws Exception;
}
