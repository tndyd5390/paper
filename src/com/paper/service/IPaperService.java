package com.paper.service;

import java.util.List;

import com.paper.dto.Paper_infoDTO;
import com.paper.dto.Writer_infoDTO;

public interface IPaperService {
	public boolean insertPaperInfoAndWriter(Paper_infoDTO pDTO, List<Writer_infoDTO> wList) throws Exception;
}
