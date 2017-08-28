package com.paper.controller.user;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
		
		String nNo = CmmUtil.nvl(req.getParameter("noticeNo"));
		
		log.info(this.getClass() + " nNo = "+ nNo);
		
		model.addAttribute("nNo",nNo);
		
		nNo = null;
		log.info(this.getClass().getName() + " userPaperReg End!!");
		return "paperReg";
	}
	
	@RequestMapping(value="paperRegProc")
	public String paperRegProc(HttpServletRequest req, HttpServletResponse res, Model model) throws Exception{
		log.info(this.getClass().getName()+ " paperRegProc Start!!");
		
		String nNo = req.getParameter("nNo");
		String paperKor = req.getParameter("korName");
		String paperEng = req.getParameter("engName");
		String writerNames[] = req.getParameterValues("name");
		String writerEmails[] = req.getParameterValues("email");
		String writerBelongs[] = req.getParameterValues("belong");
		
		log.info(nNo);
		log.info(paperKor);
		log.info(paperEng);
		
		log.info("---------------------------------");
		for(int i=0; i<writerNames.length;i++){
			String writerName = writerNames[i];
			String writerEmail = writerEmails[i];
			String writerBelong = writerBelongs[i];
			log.info(writerName);
			log.info(writerEmail);
			log.info(writerBelong);
		}
		log.info("---------------------------------");
		
		log.info(this.getClass().getName()+ " paperRegProc End!!");
		return "redirect:paperReg.do";
	}
}
