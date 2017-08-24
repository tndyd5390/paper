package com.paper.controller.user;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paper.dto.Notice_infoDTO;
import com.paper.service.INoticeService;



@Controller
public class NoticeController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="NoticeService")
	private INoticeService noticeService;
	
	@RequestMapping(value="noticeList")
	public String noticeList(HttpServletRequest req, HttpServletResponse res, Model model, HttpSession session) throws Exception{
		log.info(this.getClass().getName() + " noticeList Start!! ");
		
		List<Notice_infoDTO> nList = noticeService.getNoticeList();
		
		model.addAttribute("nList", nList);
		
		nList = null;
		log.info(this.getClass().getName() + " noticeList End!! ");
		return "noticeList";
	}
}
