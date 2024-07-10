package com.hotel.controller;

import java.util.List;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hotel.Dao.UserDetailDao;
import com.hotel.Entity.UserDetails;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController 
{
	@Autowired
	UserDetailDao userDetailDao;
	@RequestMapping("/Registrationpage")
	public String userRegistrationpage(Model model)
	{
		model.addAttribute("userdetails", new UserDetails());
		return "UserDetailsRegistration";
	}
	@RequestMapping("/registrationdetails")
	public String userRegistration(UserDetails details ,Model model)
	{
		UserDetails userRegistration = userDetailDao.insertUserRegistration(details);
		if(userRegistration!=null)
		{
			model.addAttribute("msg", "Registration Suscessfull");
			model.addAttribute("userdetails", new UserDetails());
			return "UserDetailsRegistration";
		}
		else
		{
			model.addAttribute("msg", "Registration is  not completed");
			model.addAttribute("user", new UserDetails());
			return "UserDetailsRegistration";
		}
	}
	@RequestMapping("/loginpage")
	public String userloginpage()
	{
		return "UserLogin";
	}
	@RequestMapping("/userlogin")
	public String login(String emailid,String password,Model model,HttpSession session)
	{
		UserDetails userlogin = userDetailDao.userlogin(emailid, password);
		if(userlogin!=null)
		{
			session.setAttribute("userid", userlogin);
//			List<UserDetails> getalluserdetails = userDetailDao.getalluserdetails();
//			model.addAttribute("listofusers", getalluserdetails);
//			model.addAttribute("name", userlogin.getFirstname());
			return"redirect:/displaydata";
		}
		else
		{
			model.addAttribute("name", "provide valid Details ");
			return "displaydata";
		}
	}
	@RequestMapping("/delete")
	@ResponseBody
	public String deleteuserDetails(int userid,Model model,HttpSession session)
	{
		UserDetails id  = (UserDetails) session.getAttribute("userid");
		if(id.getUserid()!=userid)
		{		
			boolean deletebyuserid = userDetailDao.deletebyuserid(userid);
			return"Deleted";
		}
		else
		{
			return"User Cannot Delete His Account";
		}
	}
	@RequestMapping("/displaydata")
	public String displayallDetails(Model model,HttpSession session)
	{
		UserDetails userlogin = (UserDetails) session.getAttribute("userid");
		session.setAttribute("userid", userlogin);
		List<UserDetails> getalluserdetails = userDetailDao.getalluserdetails();
		model.addAttribute("listofusers", getalluserdetails);
		model.addAttribute("name", userlogin.getFirstname());
		return"AllUserDetails";
	}
	
	
	@RequestMapping("/updatebyid")
	public String updateuserdetailsByUserId(int id,Model model)
	{
		UserDetails userDetailsById = userDetailDao.updatealluserdetailsbyusinguserid(id);
		if(userDetailsById!=null)
		{
			model.addAttribute("userdetails", userDetailsById);
			return "UpdateUserDetails";
		}
		else
		{
			return null;
		}
	}
	@RequestMapping("/updateUserDetailsHTMLPage")
	//@ResponseBody
	public String updateDetails(UserDetails userDetails)
	{
		UserDetails valuesinDataBase = userDetailDao.updateValuesinDataBase(userDetails);
		if(valuesinDataBase!=null)
		{ 
			return"redirect:/displaydata";
		}
		else
		{
			return null;
		}
	}
}
