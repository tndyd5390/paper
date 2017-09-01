package com.paper.controller.user;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.paper.util.CmmUtil;

@Controller
public class FileDownloadController{

	private Logger log = Logger.getLogger(this.getClass());

	@RequestMapping(value="download.do")
	public ModelAndView download(HttpServletRequest req, HttpServletResponse resp, Model model, HttpSession session) throws Exception{
		log.info(this.getClass() + ".fileDownload start!!!");
		String path = CmmUtil.nvl(req.getParameter("path"));
		log.info(this.getClass() + " path : " + path);
		String fileName = CmmUtil.nvl(req.getParameter("fileName"));
		log.info(this.getClass() + " fileName : " + fileName);
		String fileOrgName = CmmUtil.nvl(req.getParameter("fileOrgName"));
		log.info(this.getClass() + " fileOrgName");
		
		File file = new File(path + fileName);
		
		ModelAndView mav = new ModelAndView("download", "downloadFile", file);
		mav.addObject("fileOrgName", fileOrgName);
		log.info(this.getClass() + ".fileDownLoad end!!!");
		return mav;
	}	
}
