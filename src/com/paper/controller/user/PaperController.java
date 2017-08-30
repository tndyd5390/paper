package com.paper.controller.user;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.paper.dto.Paper_infoDTO;
import com.paper.dto.Writer_infoDTO;
import com.paper.service.IPaperService;
import com.paper.util.CmmUtil;


@Controller
public class PaperController {
	private Logger log = Logger.getLogger(this.getClass());
	
	String savePath = "C:\\Users\\Data3811-32\\git\\paper\\WebContent\\papers\\";
	
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
	public String paperRegProc(HttpServletRequest req, HttpServletResponse res, Model model, HttpSession session, @RequestParam("paper") MultipartFile file) throws Exception{
		log.info(this.getClass().getName()+ " .paperRegProc Start!!");
		
		String nNo = CmmUtil.nvl(req.getParameter("nNo"));
		log.info(this.getClass() + " nNo : " + nNo);
		String paperKor = CmmUtil.nvl(req.getParameter("korName"));
		log.info(this.getClass() + " paperKor : " + paperKor);
		String paperEng = CmmUtil.nvl(req.getParameter("engName"));
		log.info(this.getClass() + " paperEng : " + paperEng);
		String pType = CmmUtil.nvl(req.getParameter("pType"));
		log.info(this.getClass() + " pType : " + pType);
		
		String writerNames[] = CmmUtil.nvlArr(req.getParameterValues("name"));
		for(String writerName : writerNames){
			log.info(this.getClass() + " writerName : " + writerName);
		}
		
		String writerEmails[] = CmmUtil.nvlArr(req.getParameterValues("email"));
		for(String writerEmail : writerEmails){
			log.info(this.getClass() + " writerEmail : " + writerEmail);
		}
		
		String writerBelongs[] = CmmUtil.nvlArr(req.getParameterValues("belong"));
		for(String writerBelong : writerBelongs){
			log.info(this.getClass() + " writerBelong : " + writerBelong);
		}
		
		String userNo = CmmUtil.nvl((String)session.getAttribute("ss_user_no"));
		String reFileName = "";
		String  fileOrgName = CmmUtil.nvl(file.getOriginalFilename());
		log.info(this.getClass() + " fileOrgName : " + fileOrgName);
		
		String extended = fileOrgName.substring(fileOrgName.indexOf("."), fileOrgName.length());
		/**
		 *  파일 확장자 체크해서 다른곳으로 보내는 로직 작성하기
		 */
		String now = new SimpleDateFormat("yyyyMMddhmsS").format(new Date());
		Paper_infoDTO pDTO = new Paper_infoDTO();
		reFileName = savePath + now + extended;
		File newFile = new File(reFileName);
		file.transferTo(newFile);
		pDTO.setNotice_no(nNo);
		pDTO.setPaper_kor(paperKor);
		pDTO.setPaper_eng(paperEng);
		pDTO.setPaper_type(pType);
		pDTO.setUser_no(userNo);
		pDTO.setFile_org_name(fileOrgName);
		pDTO.setFile_path(savePath);
		pDTO.setFile_name(now + extended);
		pDTO.setPaper_ad("N");
		pDTO.setReg_user_no(userNo);
		
		List<Writer_infoDTO> wList = new ArrayList<>();
		if(writerNames.length == writerEmails.length && writerEmails.length == writerBelongs.length && writerNames.length == writerBelongs.length){
			for(int i = 0; i< writerNames.length;i++){
				Writer_infoDTO wDTO = new Writer_infoDTO();
				wDTO.setNotice_no(nNo);
				wDTO.setWriter_name(writerNames[i]);
				wDTO.setWriter_email(writerEmails[i]);
				wDTO.setWriter_belong(writerBelongs[i]);
				wDTO.setReg_user_no(userNo);
				wList.add(wDTO);
			}
		}
		boolean result = false;
		result = paperService.insertPaperInfoAndWriter(pDTO, wList);
		log.info(this.getClass().getName()+ " .paperRegProc End!!");
		return "redirect:paperReg.do";
	}
	@RequestMapping(value="paperList")
	public @ResponseBody List<Paper_infoDTO> paperList(@RequestParam(value="nNo") String nNo) throws Exception{
		log.info(this.getClass().getName() + " paperList Start!!");

		log.info(this.getClass().getName() + " nNo : " + nNo);
		Paper_infoDTO pDTO = new Paper_infoDTO();
		pDTO.setNotice_no(nNo);
		List<Paper_infoDTO> pList = paperService.getPaperList(pDTO);
		
		
		pDTO = null;
		log.info(this.getClass().getName() + " paperList End!!");
		return pList;
	}
	
	@RequestMapping(value="paperAdUpdate")
	public @ResponseBody List<Paper_infoDTO> paperAdUpdate(@RequestParam(value="nNo") String nNo, @RequestParam(value="pNo") String pNo, @RequestParam(value="pAd") String pAd)throws Exception{
		log.info(this.getClass().getName() + " paperAdUpdate Start!!");
		
		log.info(this.getClass().getName() + " nNo : " + nNo);
		log.info(this.getClass().getName() + " pNo : " + pNo);
		log.info(this.getClass().getName() + " pAd : " + pAd);

		Paper_infoDTO pDTO = new Paper_infoDTO();
		pDTO.setPaper_no(pNo);
		pDTO.setNotice_no(nNo);
		pDTO.setPaper_ad(pAd);
		List<Paper_infoDTO> pList = paperService.getUpdatePaperList(pDTO);
		
		pDTO = null;
		log.info(this.getClass().getName() + " paperAdUpdate End!!");
		return pList;
	}
	
	@RequestMapping(value="acceptList")
	public String acceptList(HttpServletRequest req, Model model) throws Exception{
		log.info(this.getClass().getName() + " acceptList Start!!");
		
		String nNo = CmmUtil.nvl(req.getParameter("nNo"));
		log.info(this.getClass().getName() + " nNo : " + nNo);
		Paper_infoDTO pDTO = new Paper_infoDTO();
		pDTO.setNotice_no(nNo);
		List<Paper_infoDTO> pList = paperService.getAcceptList(pDTO);
		
		model.addAttribute("pList", pList);
		pList = null;
		pDTO = null;
		log.info(this.getClass().getName() + " acceptList End!!");
		return "";
	}
	@RequestMapping(value="dropList")
	public String dropList(HttpServletRequest req, Model model) throws Exception{
		log.info(this.getClass().getName() + " acceptList Start!!");
		
		String nNo = CmmUtil.nvl(req.getParameter("nNo"));
		log.info(this.getClass().getName() + " nNo : " + nNo);
		Paper_infoDTO pDTO = new Paper_infoDTO();
		pDTO.setNotice_no(nNo);
		List<Paper_infoDTO> pList = paperService.getDropList(pDTO);
		
		model.addAttribute("pList", pList);
		pList = null;
		pDTO = null;
		log.info(this.getClass().getName() + " acceptList End!!");
		return "";
	}
}
