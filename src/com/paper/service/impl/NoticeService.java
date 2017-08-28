package com.paper.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paper.dto.Notice_infoDTO;
import com.paper.persistance.mapper.NoticeMapper;
import com.paper.service.INoticeService;;

@Service("NoticeService")
public class NoticeService implements INoticeService{
	@Resource(name="NoticeMapper")
	private NoticeMapper noticeMapper;

	@Override
	public List<Notice_infoDTO> getNoticeList() throws Exception {
		return noticeMapper.getNoticeList();
	}
	
	@Override
	public Notice_infoDTO selectNotice(Notice_infoDTO nDTO) throws Exception{
		return noticeMapper.selectNotice(nDTO);
	}

}
