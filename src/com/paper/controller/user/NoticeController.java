package com.paper.controller.user;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import com.paper.service.INoticeService;


@Controller
public class NoticeController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="NoticeService")
	private INoticeService noticeService;

	
}
