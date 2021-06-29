package com.kopo.oaiaGamLab;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	// 이부분 로그인시, 유저로그인, 관리자로그인 창 다르게
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main(HttpServletRequest request, Locale locale, Model model) {
		HttpSession session = request.getSession();

		Boolean is_login = (Boolean) session.getAttribute("is_login");
		String login_id = (String) session.getAttribute("login_id");

		if (is_login == null) {
			return "main";
		} else if (login_id.length() == 9 && login_id.split("_")[0].equals("admin")) {
			model.addAttribute("m1", login_id);
			return "adminMain";
		} else
			return "userMain";
	}

	@RequestMapping(value = "/createDB", method = RequestMethod.GET)
	public String createTable(Locale locale, Model model) {
		GameDB db = new GameDB();
		boolean isSuccess = db.createDB();
		if (isSuccess) {
			model.addAttribute("m1", "Complete");

		} else {
			model.addAttribute("m1", "Already exist");
		}
		return "message";
	}

	@RequestMapping(value = "/game", method = RequestMethod.GET)
	public String startGame(Locale locale, Model model) {
		return "game";
	}

	// 관리자 로그인
	@RequestMapping(value = "/adminlogin", method = RequestMethod.GET)
	public String adminAction(HttpServletRequest request, Locale locale, Model model) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		GameDB db = new GameDB();
		Admin resultSet = db.selectadminData();
		
		if (resultSet.idx < 1) { // db 비어있을 경우, 관리자 4개 id 생성
			boolean result = db.insertadminDB();
			if (result) {
				return "adminlogin";
			} else {
				model.addAttribute("m1", "DB ERROR");
				return "message";
			}
		} else { // db 있을 경우, adminlogin 창으로..
			return "adminlogin";
		}
	}

	@RequestMapping(value = "/adminlogin_action", method = RequestMethod.GET)
	public String loginAction(HttpServletRequest request, Locale locale, Model model) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String admin_id = request.getParameter("admin_id");
		String admin_pwd = request.getParameter("admin_pwd");

		Admin admin = new Admin(admin_id, admin_pwd);
		GameDB db = new GameDB();
		boolean isSuccess = db.loginadminDB(admin);

		if (isSuccess) {
			HttpSession session = request.getSession();
			session.setAttribute("is_login", true);
			session.setAttribute("login_id", admin_id);
			session.setAttribute("login_pwd", admin_pwd);

			model.addAttribute("m1", admin_id);
			return "redirect:/";

		} else {
			model.addAttribute("m1", "Login Error.");
			return "message";
		}
	}

	// 회원가입 라우터
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Locale locale, Model model) {
		return "signup";
	}

	@RequestMapping(value = "/signup_action", method = RequestMethod.POST)
	public String insertData(HttpServletRequest request, Locale locale, Model model) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String user_id = request.getParameter("user_id");
		String user_pwd = request.getParameter("user_pwd1");
		String user_name = request.getParameter("user_name");
		String user_birth = request.getParameter("user_birth");
		String user_email = request.getParameter("user_email");
		String user_nickName = request.getParameter("user_nickName");
		SimpleDateFormat sighupTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = sighupTime.format(Calendar.getInstance().getTime());
		SignupUser signupUser = new SignupUser(user_id, user_pwd, user_name, user_birth, user_email, user_nickName,
				now);
		UserDB db = new UserDB();
		String resultString = db.signup(signupUser);
		model.addAttribute("m1", resultString);
		return "message";
	}

	// 로그인 라우터
	   @RequestMapping(value = "/login", method = RequestMethod.GET)
	   public String login(Locale locale, Model model) {
	      return "login";
	   }

	   @RequestMapping(value = "/login_action", method = RequestMethod.POST)
	   public String login(HttpServletRequest request, Locale locale, Model model) {
	      try {
	         request.setCharacterEncoding("UTF-8");
	      } catch (UnsupportedEncodingException e) {
	         e.printStackTrace();
	      }
	      String user_id = request.getParameter("user_id");
	      String user_pwd = request.getParameter("user_pwd");
	      UserDB db = new UserDB();
	      int userIdx = db.userlogin(user_id, user_pwd);
	      if (userIdx>0) {
	         HttpSession session = request.getSession();
	         session.setAttribute("is_login", true);
	         session.setAttribute("login_id", user_id);
	         session.setAttribute("user_idx", userIdx);

	         return "redirect:/";
	      }
	      return "redirect:/login";
	   }
	   
	   //내정보 페이지
	   
	   //내정보 페이지 진입 하기전 비밀번호 대조 페이지
	   @RequestMapping(value = "/myinfo_pwd", method = RequestMethod.GET)
	   public String myinfoPwd(Locale locale, Model model) {
	      return "myinfo_confirm";
	   }
	   
	   @RequestMapping(value = "/confirm_action", method = RequestMethod.POST)
	   public String pwdConfirm(HttpServletRequest request, Locale locale, Model model) {
	   
	      UserDB db = new UserDB();
	      String user_pwd = request.getParameter("pwd_confirm");
	      user_pwd = db.sha256(user_pwd);
	      HttpSession session = request.getSession();
	         int idx = (Integer) session.getAttribute("user_idx");
	         SignupUser user = db.detalisData(idx);
	      if (user_pwd.equals(user.pwd)) {
	         return "redirect:/myinfo";
	      }
	      return "redirect:/myinfo_pwd";
	   }
	   
	   
	   @RequestMapping(value = "/myinfo", method = RequestMethod.GET)
	   public String myinfo(HttpServletRequest request,Locale locale, Model model) {
	      HttpSession session = request.getSession();
	         boolean isLogin = (Boolean) session.getAttribute("is_login");
	         if (isLogin) {
	            // 세션에서 idx 가져오기
	            int idx = (Integer) session.getAttribute("user_idx");
	            UserDB db = new UserDB();
	            SignupUser user = db.detalisData(idx);

	            model.addAttribute("idx", user.idx);
	            model.addAttribute("usesr_id", user.id);
	            model.addAttribute("user_pwd", user.pwd);
	            model.addAttribute("user_name", user.name);
	            model.addAttribute("user_birth", user.birth);
	            model.addAttribute("user_email", user.email);
	            model.addAttribute("user_nickName", user.nickname);
	         }

	      return "myinfo";
	   }
	   
	   @RequestMapping(value = "/update_action", method = RequestMethod.GET)
	   public String update(Locale locale, Model model
	         ,@RequestParam("idx") int idx
	         ,@RequestParam("user_pwd1") String noChangePwd
	         ,@RequestParam("user_pwd2") String ChangePwd
	         ,@RequestParam("user_name") String user_name
	         ,@RequestParam("user_birth") String user_birth
	         ,@RequestParam("user_email") String user_email
	         ,@RequestParam("user_nickName") String user_nickName) {
	      UserDB db = new UserDB();
	      if(ChangePwd=="") {
	         ChangePwd = noChangePwd;
	      }else {
	         ChangePwd = db.sha256(ChangePwd);
	      }
	      SignupUser signupUser = new SignupUser(idx, ChangePwd, user_name, user_birth, user_email, user_nickName);
	      boolean isSuccess = db.updateData(signupUser);
	      if(isSuccess) {
	         return "redirect:/";
	      }
	      return "redirect:/myinfo";
	   }

	// 로그인 라우터
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, Locale locale, Model model) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/";
	}

}
