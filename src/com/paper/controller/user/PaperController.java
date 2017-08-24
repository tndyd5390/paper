package com.paper.controller.user;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paper.service.IPaperService;
import com.paper.util.CmmUtil;


@Controller
public class PaperController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="PaperService")
	private IPaperService paperService;
	
	@RequestMapping(value="paperReg")
	public String userPaperReg(HttpServletRequest req, Model model, HttpSession session) throws Exception{
		log.info(this.getClass().getName() + " userPaperReg Start!!");
		
	//	String nNo = CmmUtil.nvl(req.getParameter("nNo"));
		
	//	model.addAttribute("nNo",nNo);
		
	//	nNo = null;
		log.info(this.getClass().getName() + " userPaperReg End!!");
		return "paperReg";
	}
}
