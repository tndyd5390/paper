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
	public String noticeList(HttpServletRequest req, HttpServletResponse resp, Model model, HttpSession session) throws Exception{
		log.info(this.getClass().getName() + " noticeList Start!! ");
		
		List<Notice_infoDTO> nList = noticeService.getNowNoticeList();
		
		
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
	
	@RequestMapping(value="adminNoticeList")
	public String adminNoticeList(HttpServletRequest req, HttpServletResponse resp, Model model) throws Exception{
		log.info(this.getClass().getName() + " adminNoticeList Start!!");
		
		List<Notice_infoDTO> nList = noticeService.getNoticeList();
		
		model.addAttribute("nList", nList);
		nList = null;
		
		log.info(this.getClass().getName() + " adminNoticeList End!!");
		return "admin/adminNoticeList";
	}
	
	@RequestMapping(value="adminNoticeReg")
	public String adminNoticeReg(HttpServletRequest req, HttpServletResponse resp, Model model) throws Exception{
		log.info(this.getClass().getName() + " adminNoticeReg Start!!");
		
		
		
		log.info(this.getClass().getName() + " adminNoticeReg End!!");
		return "admin/adminNoticeReg";
	}
	@RequestMapping(value="noticeRegProc")
	public String noticeRegProc (HttpServletRequest req, HttpServletResponse resp, Model model, HttpSession session) throws Exception{
		log.info(this.getClass().getName() + " noticeRegProc Start!!");
		
		String noticeTitle = CmmUtil.nvl(req.getParameter("title"));
		String receptionDate = CmmUtil.nvl(req.getParameter("reception_date"));
		String endDate = CmmUtil.nvl(req.getParameter("end_date"));
		String exhibitionDate = CmmUtil.nvl(req.getParameter("exhibition_date"));
		String userNo = CmmUtil.nvl((String) session.getAttribute("ss_user_no"));
		
		Notice_infoDTO nDTO = new Notice_infoDTO();
		
		nDTO.setNotice_title(noticeTitle);
		nDTO.setReception_date(receptionDate);
		nDTO.setEnd_date(endDate);
		nDTO.setExhibition_date(exhibitionDate);
		nDTO.setReg_user_no(userNo);
		
		log.info(this.getClass()+ " title = " + noticeTitle);
		log.info(this.getClass()+ " receptionDate = " + receptionDate);
		log.info(this.getClass()+ " endDate = " + endDate);
		log.info(this.getClass()+ " exhibitionDate = " + exhibitionDate);
	//	log.info(this.getClass()+ " regUserNo = " + userNo);
		
		noticeService.insertNotice(nDTO);
		
		nDTO = null;
		
		log.info(this.getClass().getName() + " noticeRegProc End!!");
		return "redirect:adminNoticeList.do";
	}
	
}
