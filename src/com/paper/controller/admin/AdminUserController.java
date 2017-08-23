package com.paper.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.paper.dto.User_infoDTO;
import com.paper.service.IUserService;

@Controller
public class AdminUserController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="UserService")
	private IUserService userService;
	
	@RequestMapping(value="test", method=RequestMethod.GET)
	public String test(HttpServletRequest req, HttpServletResponse resp, Model model, HttpSession session) throws Exception{
		log.info(this.getClass() + ".test start!!!");
		List<User_infoDTO> uList = userService.getUserList();
		if(uList == null){
			uList = new ArrayList<User_infoDTO>();
		}
		model.addAttribute("uList", uList);
		uList = null;
		log.info(this.getClass() + ".test end!!!");
		return "test";
	}
}
