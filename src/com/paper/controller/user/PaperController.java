package com.paper.controller.user;


import java.io.File;
import java.io.FileOutputStream;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.paper.dto.Notice_infoDTO;
import com.paper.dto.Paper_infoDTO;
import com.paper.dto.User_infoDTO;
import com.paper.dto.Writer_infoDTO;
import com.paper.service.INoticeService;
import com.paper.service.IPaperService;
import com.paper.service.IUserService;
import com.paper.util.CmmUtil;
import com.paper.util.MergeUtil;
import com.paper.util.WgetUtil;


@Controller
public class PaperController {
	private Logger log = Logger.getLogger(this.getClass());
	
	String savePath = "/home/paper/papers/originals";
	
	@Resource(name="PaperService")
	private IPaperService paperService;
	@Resource(name="NoticeService")
	private INoticeService noticeService;
	@Resource(name="UserService")
	private IUserService userService;

	@RequestMapping(value="paperReg")
	public String userPaperReg(HttpServletRequest req, Model model, HttpSession session) throws Exception{
		log.info(this.getClass().getName() + " userPaperReg Start!!");
		
		String nNo = CmmUtil.nvl(req.getParameter("noticeNo"));
		String userNo = CmmUtil.nvl((String)session.getAttribute("ss_user_no"));
		
		
		User_infoDTO uDTO = new User_infoDTO();
		uDTO.setUser_no(userNo);
		uDTO = userService.getUserDetail(uDTO);
		
		
		log.info(this.getClass().getName() + " nNo = "+ nNo);
		log.info(this.getClass().getName() + " userNo = "+userNo);
		
		model.addAttribute("nNo",nNo);
		model.addAttribute("uDTO", uDTO);
		
		nNo = null;
		uDTO = null;
		log.info(this.getClass().getName() + " userPaperReg End!!");
		return "paperReg";
	}
	
	@RequestMapping(value="paperRegProc", method=RequestMethod.POST)
	public String paperRegProc(HttpServletRequest req, HttpServletResponse res, Model model, HttpSession session, @RequestParam("paper") MultipartFile file) throws Exception{
		log.info(this.getClass().getName()+ " .paperRegProc Start!!");
		
		String returnUrl = "";//최종적으로 반환할 페이지 리다이렉트 할것인지 얼럿창 갈것인지
		String msg = "";//얼럿창을 간다면 얼럿 메세지
		String url = "";//얼럿 후 이동할 페이지
		
		String nNo = CmmUtil.nvl(req.getParameter("nNo"));//공고 번호
		log.info(this.getClass() + " nNo : " + nNo);
		String paperKor = CmmUtil.nvl(req.getParameter("korName"));//논문 한글 이름
		log.info(this.getClass() + " paperKor : " + paperKor);
		String paperEng = CmmUtil.nvl(req.getParameter("engName"));//논문 영어 이름
		log.info(this.getClass() + " paperEng : " + paperEng);
		String pType = CmmUtil.nvl(req.getParameter("pType"));//구두발표인지 포스터 발포인지
		log.info(this.getClass() + " pType : " + pType);
		
		String writerNames[] = CmmUtil.nvlArr(req.getParameterValues("name"));//저자들의 이름
		for(String writerName : writerNames){
			log.info(this.getClass() + " writerName : " + writerName);
		}
		
		String writerEmails[] = CmmUtil.nvlArr(req.getParameterValues("email"));//저자들의 이메일
		for(String writerEmail : writerEmails){
			log.info(this.getClass() + " writerEmail : " + writerEmail);
		}
		
		String writerBelongs[] = CmmUtil.nvlArr(req.getParameterValues("belong"));//저자들의 소속
		for(String writerBelong : writerBelongs){
			log.info(this.getClass() + " writerBelong : " + writerBelong);
		}
		
		String userNo = CmmUtil.nvl((String)session.getAttribute("ss_user_no"));//등록자 번호
		String reFileName = "";//파일 이름을 시간으로 바꿀 변수
		String  fileOrgName = CmmUtil.nvl(CmmUtil.fileNameCheck(file.getOriginalFilename()));//파일 이름을 가져와서 시큐어 검사 및 널처리
		log.info(this.getClass() + " fileOrgName : " + fileOrgName);
		
		String extended = fileOrgName.substring(fileOrgName.indexOf("."), fileOrgName.length());//파일 확장자 추출
		log.info(this.getClass() + " extended : " + extended);
		if(!extended.equals(".docx")){
			model.addAttribute("msg", ".docx파일만 업로드 가능 합니다.");
			model.addAttribute("url", "noticeList.do");
			return "alert";
		}
		
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
		
		List<Writer_infoDTO> wList = new ArrayList<Writer_infoDTO>();
		if(writerNames.length == writerEmails.length && writerEmails.length == writerBelongs.length 
				&& writerNames.length == writerBelongs.length){
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
		if(result){
			msg = "등록 성공했습니다.";
			url = "noticeList.do";
			model.addAttribute("msg", msg);
			model.addAttribute("url", url);
			returnUrl  = "admin/userAlert";
		}else{
			msg = "등록 실패 했습니다.";
			url = "paperReg.do?nNo=" + nNo;
			model.addAttribute("msg", msg);
			model.addAttribute("url", url);
			returnUrl = "admin/userAlert";
		}
		wList=null;
		writerBelongs=null;
		writerEmails=null;
		writerNames=null;
		nNo=null;
		userNo=null;
		fileOrgName=null;
		paperEng=null;
		paperKor=null;
		extended=null;
		pType=null;
		
		log.info(this.getClass().getName()+ " .paperRegProc End!!");
		return returnUrl;
	}
	@RequestMapping(value="paperList")
	public @ResponseBody List<Paper_infoDTO> paperList(@RequestParam(value="nNo") String nNo, @RequestParam(value="pAd") String pAd) throws Exception{
		log.info(this.getClass().getName() + " paperList Start!!");

		log.info(this.getClass().getName() + " nNo : " + nNo);
		log.info(this.getClass().getName() + " pAd : " + pAd);

		Paper_infoDTO pDTO = new Paper_infoDTO();
		pDTO.setNotice_no(nNo);
		pDTO.setPaper_adV(pAd);
		List<Paper_infoDTO> pList = paperService.getPaperList(pDTO);
		
		
		pDTO = null;
		nNo=null;
		pAd=null;
		log.info(this.getClass().getName() + " paperList End!!");
		return pList;
	}
	
	@RequestMapping(value="paperAdUpdate")
	public @ResponseBody List<Paper_infoDTO> paperAdUpdate(@RequestParam(value="nNo") String nNo, @RequestParam(value="pNo") String pNo, @RequestParam(value="pAd") String pAd, @RequestParam(value="pAdV") String pAdV)throws Exception{
		log.info(this.getClass().getName() + " paperAdUpdate Start!!");
		
		log.info(this.getClass().getName() + " nNo : " + nNo);
		log.info(this.getClass().getName() + " pNo : " + pNo);
		log.info(this.getClass().getName() + " pAd : " + pAd);
		// AD 상태 값을 UPDATE 날리기 위한 AD 값
		log.info(this.getClass().getName() + " pAdV : " + pAdV);
		// 현재 보고있는 페이지 뷰 AD값
		
		Paper_infoDTO pDTO = new Paper_infoDTO();
		pDTO.setPaper_no(pNo);
		pDTO.setNotice_no(nNo);
		pDTO.setPaper_ad(pAd);
		// UPDATE문 사용 DTO
		pDTO.setPaper_adV(pAdV);
		// SELECT문 사용 DTO
		List<Paper_infoDTO> pList = paperService.getUpdatePaperList(pDTO);
		// 서비스에서 UPDATE 후 SELECT 문 리턴 받음
		
		pDTO = null;
		nNo=null;
		pNo=null;
		pAd=null;
		pAdV=null;
		log.info(this.getClass().getName() + " paperAdUpdate End!!");
		return pList;
	}
	@RequestMapping(value="mergeDocxPage")
	public String mergeDockPage(HttpServletRequest req, Model model) throws Exception{
		log.info(this.getClass().getName() + " mergeDocxPage Start!!");
		String nNo = CmmUtil.nvl(req.getParameter("nNo"));
		String adV = CmmUtil.nvl(req.getParameter("adV"));
		// 합격된 논문만 SELECT 할 수 있도록 adV값 설정
		log.info(this.getClass().getName() + " nNo = "+nNo);
		log.info(this.getClass().getName() + " adV = "+adV);
		Paper_infoDTO pDTO = new Paper_infoDTO();
		pDTO.setNotice_no(nNo);
		pDTO.setPaper_adV(adV);
		
		List<Paper_infoDTO> pList = paperService.getPaperList(pDTO);
		
		model.addAttribute("pList", pList);
		pList=null;
		nNo=null;
		adV=null;
		log.info(this.getClass().getName() + " mergeDocxPage End!!");
		return "admin/adminPaperMergePop";
	}
	@RequestMapping(value="downloadDocx")
	public @ResponseBody String downloadDocx(HttpServletRequest req, Model model) throws Exception{
		log.info(this.getClass().getName()+ "downloadDocx Start!!");
		
		String nNo = CmmUtil.nvl(req.getParameter("nNo"));
		String fileNames[] = CmmUtil.nvlArr(req.getParameterValues("downFileName"));
		// Values로 순서대로 fileName들을 배열로 받아 옴
		String inputUrl = "http://116.124.128.185:8084/papers/";
		String outputPath = "/home/paper/papers/"+nNo;
	  //String outputPath = "C:\\www\\"+nNo;
		File mkDir = new File(outputPath);
		//다운 받을 디렉토리를 생성 하기위해 파일 객체 만듦
		
		if(mkDir.exists()){
			// mkDir에 해당하는 디렉토리가 있을 때
			WgetUtil.delFile(outputPath);
			// WgetUtil의 delFile 메소드로 해당 디렉토리를 삭제 함
		}
		
		mkDir.mkdirs();
		// mkdir() 메소드로 mkDir에 해당하는 디렉토리 생성
		
		for(String fileName : fileNames){
			WgetUtil.wget(inputUrl+fileName, outputPath);
			// fileNames에 저장 되어있는 파일들을 WgetUtil의 wget 메소드로 다운받음
		}
		log.info(this.getClass().getName()+ "downloadDocx End!!");
		
		return "Success";
	}
	
	@RequestMapping(value="mergeDocxProc")
	public String mergeDockProc(HttpServletRequest req, Model model) throws Exception{
		log.info(this.getClass().getName() + " mergeDocxProc Start!!");
		String nNo = CmmUtil.nvl(req.getParameter("nNo"));
		String fileNames[] = req.getParameterValues("fileName");
		// Values로 순서대로 fileName들을 배열로 받아 옴
		String outPath = "/home/paper/papers/"+nNo+"/merged/";
		String outFile = nNo + ".docx";
		// 저장해야 할 확장자는 .docx 
		log.info(this.getClass().getName()+ " nNo = " +nNo);
		for(String fileName : fileNames){
			log.info(this.getClass().getName()+ " fileName = " +fileName);
		}
		log.info(this.getClass().getName()+ " outPath = " +outPath);
		log.info(this.getClass().getName()+ " outFile = " +outFile);
		
		Notice_infoDTO nDTO = new Notice_infoDTO();
		nDTO.setNotice_no(nNo);
		nDTO.setFile_name(outFile);
		nDTO.setFile_path(outPath);
		noticeService.updateMergeFile(nDTO);
		// NOTICE_INFO 테이블에 해당 공고의 병합된 파일 이름과 경로를 저장하기 위해 UPDATE문을 날림
		File mkDir = new File(outPath);
		// 병합될 파일이 저장될 디렉토리를 만들기 위해 File 객체를 선언 함
		if(mkDir.exists()){
			// mkDir에 해당하는 디렉토리가 있을 때
			WgetUtil.delFile(outPath);
			// WgetUtil의 delFile 메소드로 해당 디렉토리를 삭제 함
		}
		mkDir.mkdirs();
		// mkdirs 메소드를 사용하여 mkDir에 해당하는 디렉토리 생성

		MergeUtil.mergeDocx(MergeUtil.inputFiles(fileNames), new FileOutputStream(new File(outPath+outFile)));
		// MergeUtil 속 mergeDocx 메소드로 fileNames에 저장된 경로의 논물들을 병합 함
		// 위에서 설정 한 outPath와 outFile로 생성 될 파일의 이름과 경로를 지정해 줌
		
		model.addAttribute("url", "adminMergeDownPop.do?nNo="+nNo);
		model.addAttribute("msg", "병합완료");
		
		log.info(this.getClass().getName() + " mergeDocxProc End!!");
		return "admin/userAlert";
	}
	@RequestMapping(value="updatePaperAdCheck")
	public @ResponseBody String updatePaperAdCheck(@RequestParam(value="nNo") String nNo, @RequestParam(value="allupAd") String allupAd, @RequestParam(value="upCheck[]") String[] upCheck)throws Exception{
		log.info(this.getClass().getName()+ " updatePaperAdCheck Start!!");
		
		Paper_infoDTO pDTO = new Paper_infoDTO();
		pDTO.setNotice_no(nNo);
		pDTO.setPaper_ad(allupAd);
		pDTO.setAllCheck(upCheck);
		log.info(this.getClass().getName()+ " nNo = "+ nNo);
		log.info(this.getClass().getName()+ " allupAd = "+ allupAd);
		for (String s : upCheck){
			log.info(this.getClass().getName()+ " upCheckPno = "+ s);
		}
		
		paperService.updateCheckAd(pDTO);
		
		pDTO=null;
		nNo=null;
		upCheck=null;
		log.info(this.getClass().getName()+ " updatePaperAdCheck End!!");
		return "Success";
	}
	@RequestMapping(value="filetest")
	public String fileTest(HttpServletRequest req, HttpServletResponse resp, Model model, HttpSession session) throws Exception{
		log.info(this.getClass() + ".filetest start!!!");
		
		log.info(this.getClass() + ".filetest end!!!");
		return "fileDownloadTest";
	}
}
