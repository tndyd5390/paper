package com.paper.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paper.dto.Paper_infoDTO;
import com.paper.dto.Writer_infoDTO;
import com.paper.persistance.mapper.PaperMapper;
import com.paper.service.IPaperService;

@Service("PaperService")
public class PaperService implements IPaperService{
	@Resource(name="PaperMapper")
	private PaperMapper paperMapper;

	@Override
	public boolean insertPaperInfoAndWriter(Paper_infoDTO pDTO, List<Writer_infoDTO> wList) throws Exception {
		boolean result = false;
		
		int paperResult = 0;
		paperResult = paperMapper.insertPaperInfo(pDTO);
		
		int writerResult = 0;
		writerResult = paperMapper.insertWriterInfo(wList);
		
		if(paperResult != 0 && writerResult != 0){
			result = true;
		}
		return result;
	}

	@Override
	public List<Paper_infoDTO> getPaperList(Paper_infoDTO pDTO) throws Exception {
		return paperMapper.getPaperList(pDTO);
	}

	@Override
	public List<Paper_infoDTO> getUpdatePaperList(Paper_infoDTO pDTO) throws Exception {
			paperMapper.updatePaperAd(pDTO);
		
		return paperMapper.getPaperList(pDTO);
	}

	@Override
	public void updateCheckAd(Paper_infoDTO pDTO) throws Exception {
		paperMapper.updateCheckAd(pDTO);
	}

}
