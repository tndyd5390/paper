package com.paper.persistance.mapper;

import java.util.List;

import com.paper.config.Mapper;
import com.paper.dto.Paper_infoDTO;
import com.paper.dto.Writer_infoDTO;

@Mapper("PaperMapper")
public interface PaperMapper {
	public int insertPaperInfo(Paper_infoDTO pDTO) throws Exception;

	public int insertWriterInfo(List<Writer_infoDTO> wList) throws Exception;

	public List<Paper_infoDTO> getPaperList(Paper_infoDTO pDTO) throws Exception;

	public void updatePaperAd(Paper_infoDTO pDTO) throws Exception;

}
