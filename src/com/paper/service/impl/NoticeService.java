package com.paper.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paper.persistance.mapper.NoticeMapper;
import com.paper.service.INoticeService;;

@Service("NoticeService")
public class NoticeService implements INoticeService{
	@Resource(name="NoticeMapper")
	private NoticeMapper noticeMapper;

}
