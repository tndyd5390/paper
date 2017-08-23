package com.paper.service;

import java.util.List;

import com.paper.dto.User_infoDTO;

public interface IUserService {
	public List<User_infoDTO> getUserList() throws Exception;
}
