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
import com.paper.util.EmailSender;

@Controller
public class UserController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="UserService")
	private IUserService userService;
	
	@Autowired
	private EmailSender emailSender;

	@RequestMapping(value="userSignIn")
	public String userSignIn(HttpSession session, HttpServletRequest req, HttpServletResponse resp, Model model) throws Exception{
		log.info("userSignIn Start !!!");
		log.info("userSignIn End !!!");
		return "userSignIn";
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
		User_infoDTO uDTO = new User_infoDTO();
		uDTO.setEmail(email);
		int check = userService.overlapEmail(uDTO);
		resp.getWriter().print(check);
		resp.getWriter().flush();
		resp.getWriter().close();
		uDTO = null;
		log.info(this.getClass().getName() + " [ajax] overlapEmail end");
	}

	@RequestMapping(value="userSignInProc")
	public String userSignInProc(HttpSession session, HttpServletRequest req, HttpServletResponse resp, Model model) throws Exception{
		log.info(this.getClass().getName() + " userSignInProc Start!! ");
		
		String email = CmmUtil.nvl(req.getParameter("email"));
		String password = CmmUtil.nvl(req.getParameter("password"));
		String user_name = CmmUtil.nvl(req.getParameter("user_name"));
		String belong = CmmUtil.nvl(req.getParameter("belong"));
		String phone1 = CmmUtil.nvl(req.getParameter("phone1"));
		String phone2 = CmmUtil.nvl(req.getParameter("phone2"));
		String phone3 = CmmUtil.nvl(req.getParameter("phone3"));
		
		User_infoDTO uDTO = new User_infoDTO();
		
		uDTO.setEmail(email);
		uDTO.setPassword(password);
		uDTO.setUser_name(user_name);
		uDTO.setBelong(belong);
		uDTO.setPhone_1(phone1);
		uDTO.setPhone_2(phone2);
		uDTO.setPhone_3(phone3);
		
		userService.insertUser(uDTO);
		if(uDTO == null){
			uDTO = new User_infoDTO();
		}
		
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
		
}
