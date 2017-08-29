package com.paper.service;

import java.util.List;

import com.paper.dto.Notice_infoDTO;

public interface INoticeService {

	public List<Notice_infoDTO> getNoticeList() throws Exception;

	public Notice_infoDTO selectNotice(Notice_infoDTO nDTO) throws Exception;

	public void insertNotice(Notice_infoDTO nDTO) throws Exception;

	public List<Notice_infoDTO> getNowNoticeList() throws Exception;
}
