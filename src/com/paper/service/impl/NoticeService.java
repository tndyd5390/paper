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

	@Override
	public void insertNotice(Notice_infoDTO nDTO) throws Exception {
		noticeMapper.insertNotice(nDTO);
	}
	
	@Override
	public boolean deleteAdminAllCheck(Notice_infoDTO nDTO) throws Exception{
		int result = noticeMapper.deleteAdminAllCheck(nDTO);
		if(result != 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public List<Notice_infoDTO> getNowNoticeList() throws Exception {
		return noticeMapper.getNowNoticeList();
	}

	@Override
	public Notice_infoDTO getNoticeDetail(Notice_infoDTO nDTO) throws Exception {
		return noticeMapper.getNoticeDetail(nDTO);
	}

	@Override
	public void updateMergeFile(Notice_infoDTO nDTO) throws Exception {
		 noticeMapper.updateMergeFile(nDTO);
	}


}
