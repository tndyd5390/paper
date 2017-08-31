package com.paper.persistance.mapper;

import java.util.List;

import com.paper.config.Mapper;
import com.paper.dto.Notice_infoDTO;

@Mapper("NoticeMapper")
public interface NoticeMapper {

	public List<Notice_infoDTO> getNoticeList() throws Exception;
	
	public Notice_infoDTO selectNotice(Notice_infoDTO nDTO) throws Exception;

	public void insertNotice(Notice_infoDTO nDTO) throws Exception;

	public int deleteAdminAllCheck(Notice_infoDTO nDTO) throws Exception;
	
	public List<Notice_infoDTO> getNowNoticeList() throws Exception;

	public Notice_infoDTO getNoticeDetail(Notice_infoDTO nDTO) throws Exception;

	public void updateMergeFile(Notice_infoDTO nDTO) throws Exception;

	public Notice_infoDTO getFileDown(Notice_infoDTO nDTO);

}
