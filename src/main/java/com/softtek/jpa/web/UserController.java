package com.softtek.jpa.web;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.softtek.jpa.domain.User;
import com.softtek.jpa.domain.UserRole;
import com.softtek.jpa.services.UserRolService;
import com.softtek.jpa.services.UserService;



/**
 * Handles requests for the application home page.
 */
@RequestMapping(value="/User")
@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRolService userRoleService;
	
	/*@Autowired
	UserRolService userRoleImpl;*/
	


	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String root() {
		return "home";
	}
	@RequestMapping(value="/List")
	public String List() {
		return "userList";
	}
	
	
	@RequestMapping(value="/ListUsers", method= RequestMethod.GET)
	public ResponseEntity<?> userList(){
		List<User> userList = userService.userList();
		if(userList.isEmpty()){
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
		
	}


	@RequestMapping(value="/edit")
	public String editUser(@RequestParam String username,@RequestParam String status, HttpServletRequest request) {
		User user = userService.user(username);
		List<UserRole> userRoleList = userRoleService.userRoleList();
		List<String> listStatus = userRoleService.statusList();
		request.setAttribute("users", user);
		request.setAttribute("userRole", userRoleList);
		request.setAttribute("listStatus", listStatus);
		request.setAttribute("status", status);
		return "userEdit";
		
	}

	 @RequestMapping(value="/create")
	 public ModelAndView create(@RequestParam String status) {

		/* List<UserRole> userRoleList = userRoleService.list();
		 List<String> listStatus = userService.statusList();
		 Map<String,Object>map=new HashMap<String, Object>();
		 map.put("userRole", userRoleList);
		 map.put("listStatus", listStatus);
		 map.put("status", status);
		 return new ModelAndView("jsp/"+"User"+"/create","map",map);*/
		 return null;
	 }
	 
	 
	 @RequestMapping(value="/update" ,method=RequestMethod.POST)
	 public String updateUser(@ModelAttribute User user, HttpServletRequest  request) {

		 final String user_role_id = new String(request.getParameter("userRole"));
		if( userService.update(user, user_role_id)){
			return "redirect:/User/List";
		}
		 return "redirect:/User/edit?username="+user.getUsername()+"&status=Not valid";

	 }
	 
	 @RequestMapping(value="/add" ,method=RequestMethod.POST)
	 public String addUser(@ModelAttribute User user,@RequestParam String userRoleId,@RequestParam String description ) {
		/*System.out.println(user.getRole());
		 
		 UserRole userRole =new UserRole();
		 userRole.setUserRoleId(userRoleId);
		 userRole.setDescription(description);
		 user.setRole(userRole);
		 if(  userService.update(user)){
			 return "redirect:/User/List";	
		 }
		 return "redirect:/User/create?status=Not Valid";*/return null;
	 }
}
