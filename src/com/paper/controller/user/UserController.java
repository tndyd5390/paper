package com.paper.controller.user;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paper.dto.User_infoDTO;
import com.paper.service.IUserService;
import com.paper.util.CmmUtil;
import com.paper.util.Email;
import com.paper.util.EmailSender;

@Controller
public class UserController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="UserService")
	private IUserService userService;
	
	@Autowired
	private EmailSender emailSender;

	@RequestMapping(value="userJoin")
	public String userSignIn(HttpSession session, HttpServletRequest req, HttpServletResponse resp, Model model) throws Exception{
		log.info("userSignIn Start !!!");
		log.info("userSignIn End !!!");
		return "join";
	}
	
	@RequestMapping(value="userLogin")
	public String userLogin(HttpSession session, HttpServletRequest req, HttpServletResponse resp,Model model) throws Exception{
		log.info(this.getClass().getName() + " userLogin Start!! ");
		log.info(this.getClass().getName() + " userLogin End!! ");
		return "login";
	}
	@RequestMapping(value="userLoginProc")
	public String userLoginProc(HttpSession session, HttpServletResponse resp, HttpServletRequest req, Model model) throws Exception{
		log.info(this.getClass().getName() + " userLoginProc Start!! ");
		String email = CmmUtil.nvl(req.getParameter("email"));
		String password = CmmUtil.nvl(req.getParameter("password"));
		User_infoDTO uDTO = new User_infoDTO();
		uDTO.setEmail(email);
		uDTO.setPassword(password);
		uDTO = userService.getLoginInfo(uDTO);
		
		if(uDTO==null){
			log.info(this.getClass().getName() + " userLoginFail");
			
			return "redirect:userLogin.do";
			
		}else{
			session.setAttribute("ss_user_no", uDTO.getUser_no());
			session.setAttribute("ss_user_name", uDTO.getUser_name());
			session.setAttribute("ss_user_auth", uDTO.getAuth());
			session.setAttribute("ss_user_belong", uDTO.getBelong());
			
			uDTO=null;
			log.info(this.getClass().getName() + " userLoginProc End!! ");
			return "redirect:test.do";
		}
	}
	@RequestMapping(value = "overlapEmail")
	public void overlapEmail(HttpSession session, HttpServletRequest req, HttpServletResponse resp, Model model)
			throws Exception {
		log.info(this.getClass().getName() + " [ajax] overlapEmail start");
		String email = CmmUtil.nvl(req.getParameter("email"));
		System.out.println(email);
		User_infoDTO uDTO = new User_infoDTO();
		uDTO.setEmail(email);
		int check = userService.overlapEmail(uDTO);
		resp.getWriter().print(check);
		resp.getWriter().flush();
		resp.getWriter().close();
		uDTO = null;
		log.info(this.getClass().getName() + " [ajax] overlapEmail end");
	}

	@RequestMapping(value="userJoinProc")
	public String userSignInProc(HttpSession session, HttpServletRequest req, HttpServletResponse resp, Model model) throws Exception{
		log.info(this.getClass().getName() + " userSignInProc Start!! ");
		
		String email = CmmUtil.nvl(req.getParameter("email"));
		String password = CmmUtil.nvl(req.getParameter("password"));
		String user_name = CmmUtil.nvl(req.getParameter("user_name"));
		String belong = CmmUtil.nvl(req.getParameter("belong"));
		String phone = CmmUtil.nvl(req.getParameter("phone"));
		
		User_infoDTO uDTO = new User_infoDTO();
		uDTO.setEmail(email);
		uDTO.setPassword(password);
		uDTO.setUser_name(user_name);
		uDTO.setBelong(belong);
		uDTO.setPhone(phone);
		userService.insertUser(uDTO);
		
		uDTO = null;
		log.info(this.getClass().getName() + " userSignInProc End! ");
		return "redirect:userLogin.do";
	}
	
	@RequestMapping(value="paperReg")
	public String paperReg(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model)throws Exception{
	
		return "paperReg";
	}
	@RequestMapping(value="paperSubmit")
	public String paperSubmit(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model)throws Exception{
		
		return "paperSubmit";
	}
	@RequestMapping(value="paperList")
	public String paperList(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Model model)throws Exception{
		
		return "paperList";
	}

	@RequestMapping(value="userFindInfo")
	public String userFindInfo (HttpServletRequest req, HttpServletResponse resp) throws Exception{
		log.info(this.getClass().getName() + " userFindInfo start");
		
		log.info(this.getClass().getName() + " userFindInfo end");
		return "userFindInfo";
	}
		
	@RequestMapping(value="userFindPw")
	public String adminUserFindInPw (HttpServletRequest req, HttpServletResponse resp, Model model) throws Exception{
		log.info(this.getClass().getName() + " adminUserInfo start");

		Email sandEmail = new Email();
		
		String email = CmmUtil.nvl(req.getParameter("email"));
		String user_name = CmmUtil.nvl(req.getParameter("user_name"));
		String phone = CmmUtil.nvl(req.getParameter("phone"));
		
		log.info("email = " + email);
		log.info("user_name = " + user_name);
		log.info("phone = " + phone);
				
		User_infoDTO uDTO = new User_infoDTO();
		uDTO.setEmail(email);
		uDTO.setUser_name(user_name);
		uDTO.setPhone(phone);
		uDTO = userService.getUserFindPw(uDTO);
		
		if(uDTO == null){
			return "userFindInfo.do";
		}
		else{
			log.info("password = " + uDTO.getPassword());
			sandEmail.setSubject("임시 비밀번호 알림 메일입니다.");
			sandEmail.setReciver(email);
			sandEmail.setContent("임시 비밀번호는 " + uDTO.getPassword() + "입니다.");
			
			emailSender.SendEmail(sandEmail);
			
			uDTO = null;
			
		}
		
		log.info(this.getClass().getName() + " adminUserInfo end");
		return "redirect:userLogin.do";
	}

	
}
