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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paper.dto.Notice_infoDTO;
import com.paper.service.INoticeService;
import com.paper.util.CmmUtil;



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
	
	@RequestMapping(value="selectNotice")
	public @ResponseBody Notice_infoDTO selectNotice(@RequestParam(value="nNo") String nNo) throws Exception{
		log.info(this.getClass().getName() + "selectNotice start!!" );
		log.info(nNo);
		
		Notice_infoDTO nDTO = new Notice_infoDTO();
		nDTO.setNotice_no(nNo);
		nDTO = noticeService.selectNotice(nDTO);
		
		log.info(nDTO.getEnd_date());
		
		log.info(this.getClass().getName() + "selectNotice end!!" );
		
		return nDTO;
	}
}
