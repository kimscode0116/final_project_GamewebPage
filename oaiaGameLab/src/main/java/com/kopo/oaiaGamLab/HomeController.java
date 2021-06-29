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

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	// 이부분 로그인시, 유저로그인, 관리자로그인 창 다르게
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main(HttpServletRequest request, Locale locale, Model model) {
		HttpSession session = request.getSession();
		String is_login = (String) session.getAttribute("is_login");
		String login_id = (String) session.getAttribute("login_id");

		if (is_login == null) {
			System.out.println("여기 걸리나");
			return "main";
		} else if (login_id.length() == 9 && login_id.split("_")[0].equals("admin")) {
			model.addAttribute("m1", login_id);
			System.out.println("2222여기걸리나");
			return "adminMain";
		}
		System.out.println("2332여기걸리나");
		return "main";
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

	/*
	 * @RequestMapping(value = "/login", method = RequestMethod.GET) public String
	 * loginPage(Locale locale, Model model) { return "login"; }
	 */

	// 관리자 로그인
	@RequestMapping(value = "/adminlogin", method = RequestMethod.GET)
	public String adminAction(HttpServletRequest request, Locale locale, Model model) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		GameDB db = new GameDB();

		int resultSet = db.selectadminData();
		if (resultSet < 1) { // db 비어있을 경우, 관리자 4개 id 생성
			boolean result = db.insertadminDB();
			return "adminlogin";
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
			return "message";

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
		boolean isSuccess = db.userlogin(user_id, user_pwd);
		if (isSuccess) {
			HttpSession session = request.getSession();
			session.setAttribute("is_login", true);
			return "redirect:/";
		}
		return "redirect:/login";
	}

}
