package com.kopo.oaiaGamLab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import org.springframework.web.bind.annotation.ModelAttribute;
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
			model.addAttribute("userOradmin", "<a href=\"login\">로그인</a> <a href=\"signup\">회원가입</a>");
			model.addAttribute("gameCheck1", "location.href='/oaiaGameLab/game_NoScore'");
			model.addAttribute("gameCheck2", "location.href='/oaiaGameLab/game_NoScore_space'");
			return "main";
		} else if (login_id.length() == 9 && login_id.split("_")[0].equals("admin")) {
			model.addAttribute("userOradmin",
					"<a href=\"logout\">관리자 로그아웃</a>  <a href=\"admininfo_pwd\">관리자 정보 수정</a> <a href=\"userList\">회원목록 조회</a>");
			model.addAttribute("gameCheck1", "location.href='/oaiaGameLab/game_adminScore'");
			model.addAttribute("gameCheck2", "location.href='/oaiaGameLab/game_adminScore_space'");

			model.addAttribute("m1", login_id + "님의 관리자모드");
			return "main";
		} else
			model.addAttribute("userOradmin", "<a href=\"logout\">로그아웃</a><a href=\"myinfo_pwd\">개인정보수정</a>\n");
		model.addAttribute("gameCheck1", "location.href='/oaiaGameLab/game_userScore'");
		model.addAttribute("gameCheck2", "location.href='/oaiaGameLab/game_userScore_space'");

		return "main";
	}

	@RequestMapping(value = "/createDB", method = RequestMethod.GET)
	public String createTable(HttpServletRequest request, Locale locale, Model model) {
		AdminDB db = new AdminDB();
		HttpSession session = request.getSession();

		Boolean is_login = (Boolean) session.getAttribute("is_login");
		String login_id = (String) session.getAttribute("login_id");

		boolean isSuccess = db.createDB();
		if (isSuccess) {
			model.addAttribute("m1", "Complete");
		} else {
			model.addAttribute("m1", "Already exist");
		}

		if (is_login == null) {
			model.addAttribute("userOradmin", "<a href=\"login\">로그인</a> <a href=\"signup\">회원가입</a>");
		} else if (login_id.length() == 9 && login_id.split("_")[0].equals("admin")) {
			model.addAttribute("userOradmin",
					"<a href=\"logout\">관리자 로그아웃</a>  <a href=\"admininfo_pwd\">관리자 정보 수정</a> <a href=\"userList\">회원목록 조회</a>");
		} else
			model.addAttribute("userOradmin", "<a href=\"logout\">로그아웃</a><a href=\"myinfo_pwd\">개인정보수정</a>\n");
		return "message";
	}

	// 관리자 로그인
	@RequestMapping(value = "/adminlogin", method = RequestMethod.GET)
	public String adminAction(HttpServletRequest request, Locale locale, Model model) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		AdminDB db = new AdminDB();
		Admin resultSet = db.selectadminData();

		if (resultSet.idx < 1) { // db 비어있을 경우, 관리자 4개 id 생성
			boolean result = db.insertadminDB();
			if (result) {
				return "adminlogin";
			} else {
				model.addAttribute("m1", "DB ERROR");
				model.addAttribute("userOradmin", "<a href=\"login\">로그인</a> <a href=\"signup\">회원가입</a>");
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
		AdminDB db = new AdminDB();
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
			model.addAttribute("userOradmin", "<a href=\"login\">로그인</a> <a href=\"signup\">회원가입</a>");
			model.addAttribute("messageBtn", "<a href='adminlogin' class='goGameBtn'>return Login</a>");

			return "message";
		}
	}

	// 관리자 정보 페이지

	// 관리자 정보 수정 페이지 진입 전 비밀번호 대조 페이지
	@RequestMapping(value = "/admininfo_pwd", method = RequestMethod.GET)
	public String admininfoPwd(HttpServletRequest request, Locale locale, Model model) {
		HttpSession session = request.getSession();

		Boolean is_login = (Boolean) session.getAttribute("is_login");
		String login_id = (String) session.getAttribute("login_id");

		model.addAttribute("userOradmin",
				"<a href=\"logout\">관리자 로그아웃</a>  <a href=\"admininfo_pwd\">관리자 정보 수정</a> <a href=\"userList\">회원목록 조회</a>");
		return "admininfo_confirm";
	}

	@RequestMapping(value = "/adminconfirm_action", method = RequestMethod.POST)
	public String adminpwdConfirm(HttpServletRequest request, Locale locale, Model model) {

		AdminDB db = new AdminDB();
		String inputadmin_pwd = request.getParameter("pwd_confirm");
		HttpSession session = request.getSession();
		String admin_pwd = (String) session.getAttribute("login_pwd");

		if (inputadmin_pwd.equals(admin_pwd)) {
			model.addAttribute("userOradmin",
					"<a href=\"logout\">관리자 로그아웃</a>  <a href=\"admininfo_pwd\">관리자 정보 수정</a> <a href=\"userList\">회원목록 조회</a>");

			return "redirect:/admininfo";
		}
		model.addAttribute("userOradmin",
				"<a href=\"logout\">관리자 로그아웃</a>  <a href=\"admininfo_pwd\">관리자 정보 수정</a> <a href=\"userList\">회원목록 조회</a>");

		return "admininfo_pwd";
	}

	// 관리자 정보 수정 페이지
	@RequestMapping(value = "/admininfo", method = RequestMethod.GET)
	public String admininfo(HttpServletRequest request, Locale locale, Model model) {
		HttpSession session = request.getSession();
		boolean isLogin = (Boolean) session.getAttribute("is_login");
		if (isLogin) {
			// 세션에서 id 가져오기
			String admin_id = (String) session.getAttribute("login_id");
			AdminDB db = new AdminDB();
			Admin admin = db.detalisAdmin(admin_id);
			model.addAttribute("admin_id", admin.admin_id);
			model.addAttribute("admin_pwd", admin.admin_pwd);
			model.addAttribute("admin_name", admin.admin_name);
			model.addAttribute("userOradmin",
					"<a href=\"logout\">관리자 로그아웃</a>  <a href=\"admininfo_pwd\">관리자 정보 수정</a> <a href=\"userList\">회원목록 조회</a>");

		}
		model.addAttribute("userOradmin",
				"<a href=\"logout\">관리자 로그아웃</a>  <a href=\"admininfo_pwd\">관리자 정보 수정</a> <a href=\"userList\">회원목록 조회</a>");

		return "admininfo";
	}

	@RequestMapping(value = "/adminupdate_action", method = RequestMethod.POST)
	public String adminupdat(HttpServletRequest request, Locale locale, Model model,
			@RequestParam("admin_pwd1") String noChangePwd, @RequestParam("admin_pwd2") String ChangePwd,
			@RequestParam("admin_name") String admin_name) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		String admin_id = (String) session.getAttribute("login_id");
		AdminDB db = new AdminDB();
		if (ChangePwd == "") {
			ChangePwd = noChangePwd;
		} else {
			ChangePwd = ChangePwd;
		}
		Admin admin = new Admin(admin_id, ChangePwd, admin_name);
		boolean isSuccess = db.updateAdmin(admin);
		if (isSuccess) {
			model.addAttribute("userOradmin",
					"<a href=\"logout\">관리자 로그아웃</a>  <a href=\"admininfo_pwd\">관리자 정보 수정</a> <a href=\"userList\">회원목록 조회</a>");
			
			return "redirect:/";
		}
		model.addAttribute("userOradmin",
				"<a href=\"logout\">관리자 로그아웃</a>  <a href=\"admininfo_pwd\">관리자 정보 수정</a> <a href=\"userList\">회원목록 조회</a>");
		
		return "redirect:/admininfo";
	}

	// 관리자 권한 - 전체 회원 정보 조회
	@RequestMapping(value = "/userList", method = RequestMethod.GET)
	public String selectuser(Locale locale, Model model) {
		AdminDB userdb = new AdminDB();
		String htmlString = userdb.selectuserData();
		model.addAttribute("userList", htmlString);
		return "userList";
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
		model.addAttribute("userOradmin", "<a href=\"login\">로그인</a> <a href=\"signup\">회원가입</a>");

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
		if (userIdx > 0) {
			HttpSession session = request.getSession();
			session.setAttribute("is_login", true);
			session.setAttribute("login_id", user_id);
			session.setAttribute("login_pwd", user_pwd);
			session.setAttribute("user_idx", userIdx);

			return "redirect:/";
		}
		model.addAttribute("m1", "Login Error");
		model.addAttribute("messageBtn", "<a href='login' class='goGameBtn'>return Login</a>");
		model.addAttribute("userOradmin", "<a href=\"login\">로그인</a> <a href=\"signup\">회원가입</a>");
		return "message";
	}

	// 내정보 페이지
	// 내정보 페이지 진입 하기전 비밀번호 대조 페이지
	@RequestMapping(value = "/myinfo_pwd", method = RequestMethod.GET)
	public String myinfoPwd(Locale locale, Model model) {
		return "myinfo_confirm";
	}

	@RequestMapping(value = "/confirm_action", method = RequestMethod.POST)
	public String pwdConfirm(HttpServletRequest request, Locale locale, Model model) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
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
	public String myinfo(HttpServletRequest request, Locale locale, Model model) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
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

	@RequestMapping(value = "/update_action", method = RequestMethod.POST)
	public String update(HttpServletRequest request, Locale locale, Model model, @RequestParam("idx") int idx,
			@RequestParam("user_pwd1") String noChangePwd, @RequestParam("user_pwd2") String ChangePwd,
			@RequestParam("user_name") String user_name, @RequestParam("user_birth") String user_birth,
			@RequestParam("user_email") String user_email, @RequestParam("user_nickName") String user_nickName) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		UserDB db = new UserDB();
		if (ChangePwd == "") {
			ChangePwd = noChangePwd;
		} else {
			ChangePwd = db.sha256(ChangePwd);
		}
		SignupUser signupUser = new SignupUser(idx, ChangePwd, user_name, user_birth, user_email, user_nickName);
		boolean isSuccess = db.updateData(signupUser);
		if (isSuccess) {
			return "redirect:/";
		}
		return "redirect:/myinfo";
	}

	// 로그아웃 라우터
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, Locale locale, Model model) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/";
	}
//
//	// 자유게시판
//	@RequestMapping(value = "/boardList", method = RequestMethod.GET)
//	public String selectData(HttpServletRequest request, Locale locale, Model model) {
//		try {
//			request.setCharacterEncoding("UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		HttpSession session = request.getSession();
//		Boolean is_login = (Boolean) session.getAttribute("is_login");
//		String login_id = (String) session.getAttribute("login_id");
//
//		UserDB userdb = new UserDB();
//		String htmlString1 = userdb.boardSelect();
//		String htmlString2 = userdb.noticeSelect();
//
//		if (is_login == null) {
//			model.addAttribute("userOradmin", "<a href=\"login\">로그인</a> <a href=\"signup\">회원가입</a>");
//			model.addAttribute("boardList_admin", htmlString2);
//			model.addAttribute("boardList_user", htmlString1);
//			return "boardList";
//		} else if (login_id.length() == 9 && login_id.split("_")[0].equals("admin")) {
//			model.addAttribute("userOradmin",
//					"<a href=\"logout\">관리자 로그아웃</a>  <a href=\"admininfo_pwd\">관리자 정보 수정</a> <a href=\"userList\">회원목록 조회</a>");
//			model.addAttribute("boardList_admin", htmlString2);
//			model.addAttribute("boardList_user", htmlString1);
//			return "boardList";
//		} else
//			model.addAttribute("userOradmin", "<a href=\"logout\">로그아웃</a><a href=\"myinfo_pwd\">개인정보수정</a>\n");
//		model.addAttribute("boardList_admin", htmlString2);
//		model.addAttribute("boardList_user", htmlString1);
//		return "boardList";
//	}

	// 자유게시판
	@RequestMapping(value = "/boardList", method = RequestMethod.GET)
	public String selectData(HttpServletRequest request, Locale locale, Model model) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		Boolean is_login = (Boolean) session.getAttribute("is_login");
		String login_id = (String) session.getAttribute("login_id");

		UserDB userdb = new UserDB();
		String htmlString1 = userdb.boardSelect();
		String htmlString2 = userdb.noticeSelect();

		int totalContentsCount = userdb.boardPaging();

		int pageCount = 1;
		if (totalContentsCount > 10 && totalContentsCount % 10 > 0) {
			pageCount += totalContentsCount / 10;
		}
		String pageTag = "";
		for (int i = 1; i <= pageCount; i++) {
			pageTag += "<a href='boardPagingList?pageIdx=" + i + "'>" + i + " " + "</a>";
		}
		model.addAttribute("pageCount", pageTag);

		if (is_login == null) {
			model.addAttribute("userOradmin", "<a href=\"login\">로그인</a> <a href=\"signup\">회원가입</a>");
		} else if (login_id.length() == 9 && login_id.split("_")[0].equals("admin")) {
			model.addAttribute("userOradmin",
					"<a href=\"logout\">관리자 로그아웃</a>  <a href=\"admininfo_pwd\">관리자 정보 수정</a> <a href=\"userList\">회원목록 조회</a>");
		} else
			model.addAttribute("userOradmin", "<a href=\"logout\">로그아웃</a><a href=\"myinfo_pwd\">개인정보수정</a>\n");
		model.addAttribute("boardList_admin", htmlString2);
		model.addAttribute("boardList_user", htmlString1);
		return "boardList";
	}

	// 자유게시판 페이징 처리
	@RequestMapping(value = "/boardPagingList", method = RequestMethod.GET)
	public String selectBoardPageData(HttpServletRequest request, Locale locale, Model model,
			@RequestParam("pageIdx") int pageIdx) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		Boolean is_login = (Boolean) session.getAttribute("is_login");
		String login_id = (String) session.getAttribute("login_id");

		UserDB userdb = new UserDB();

		String htmlString1 = userdb.boardPageSelect(pageIdx);
		String htmlString2 = userdb.noticeSelect();

		int totalContentsCount = userdb.boardPaging();

		int pageCount = 1;
		if (totalContentsCount > 10 && totalContentsCount % 10 > 0) {
			pageCount += totalContentsCount / 10;
		}
		String pageTag = "";
		for (int i = 1; i <= pageCount; i++) {
			pageTag += "<a href='boardPagingList?pageIdx=" + i + "'>" + i + " " + "</a>";
		}
		model.addAttribute("pageCount", pageTag);

		if (is_login == null) {
			model.addAttribute("userOradmin", "<a href=\"login\">로그인</a> <a href=\"signup\">회원가입</a>");
		} else if (login_id.length() == 9 && login_id.split("_")[0].equals("admin")) {
			model.addAttribute("userOradmin",
					"<a href=\"logout\">관리자 로그아웃</a>  <a href=\"admininfo_pwd\">관리자 정보 수정</a> <a href=\"userList\">회원목록 조회</a>");
		} else
			model.addAttribute("userOradmin", "<a href=\"logout\">로그아웃</a><a href=\"myinfo_pwd\">개인정보수정</a>\n");
		model.addAttribute("boardList_admin", htmlString2);
		model.addAttribute("boardList_user", htmlString1);
		return "boardList";
	}

	@RequestMapping(value = "/boardInsert", method = RequestMethod.GET)
	public String boardInsert(HttpServletRequest request, Locale locale, Model model) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		UserDB userdb = new UserDB();
		AdminDB gamedb = new AdminDB();

		Boolean isSuccess = (Boolean) session.getAttribute("is_login");

		String login_id = (String) session.getAttribute("login_id");

		if (isSuccess == null) {
			model.addAttribute("user_nickname", "no name");
			model.addAttribute("userOradmin", "<a href=\"login\">로그인</a> <a href=\"signup\">회원가입</a>");

			return "boardInsert";
		} else {
			if (login_id.length() == 9 && login_id.split("_")[0].equals("admin")) { // 관리자 로그인
				model.addAttribute("userOradmin",
						"<a href=\"logout\">관리자 로그아웃</a>  <a href=\"admininfo_pwd\">관리자 정보 수정</a> <a href=\"userList\">회원목록 조회</a>");
				model.addAttribute("user_nickname", "관리자");
				return "boardInsert";
			} else { // 유저 로그인
				model.addAttribute("userOradmin", "<a href=\"logout\">로그아웃</a><a href=\"myinfo_pwd\">개인정보수정</a>\n");
				model.addAttribute("user_nickname", login_id);
				return "boardInsert";
			}
		}
	}

	@RequestMapping(value = "/boardInsert_action", method = RequestMethod.POST)
	public String boardinsertAction(HttpServletRequest request, Locale locale, Model model) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		Boolean isSuccess = (Boolean) session.getAttribute("is_login");
		String login_id = (String) session.getAttribute("login_id");

		UserDB userdb = new UserDB();
		SignupUser user = new SignupUser(login_id);
		String user_idx = "";

		if (isSuccess == null) {
			model.addAttribute("userOradmin", "<a href=\"login\">로그인</a> <a href=\"signup\">회원가입</a>");
			user_idx = "noname";
		} else {
			if (login_id.length() == 9 && login_id.split("_")[0].equals("admin")) { // 관리자 로그인
				model.addAttribute("userOradmin",
						"<a href=\"logout\">관리자 로그아웃</a>  <a href=\"admininfo_pwd\">관리자 정보 수정</a> <a href=\"userList\">회원목록 조회</a>");
				user_idx = "관리자";
			} else {
				model.addAttribute("userOradmin", "<a href=\"logout\">로그아웃</a><a href=\"myinfo_pwd\">개인정보수정</a>\n");
				user_idx = userdb.nicknameSelect(user);
			}
		}
		String user_title = request.getParameter("user_title");
		String user_content = request.getParameter("user_content");

		// 첫번째created 시간을 불러오는 메소드 추가
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String now = sdf.format(Calendar.getInstance().getTime());

		Board board = new Board(user_title, login_id, user_content, now);
		boolean insertSuccess = userdb.boardInsert(board);

		if (insertSuccess) {
			return "redirect:/boardList";
		} else
			model.addAttribute("m1", "게시글 작성에 실패하였습니다");
		return "message";
	}

	// 게시물 목록에서 하나 클릭시 게시물 조회 페이지로 이동
	@RequestMapping(value = "/boarddetail", method = RequestMethod.GET)
	public String boardDetail(HttpServletRequest request, Locale locale, Model model,
			@RequestParam("idx") int contentsIdx) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		HttpSession session = request.getSession();
		UserDB userdb = new UserDB();
		AdminDB admindb = new AdminDB();

		Boolean isSuccess = (Boolean) session.getAttribute("is_login");
		String login_id = (String) session.getAttribute("login_id");
		Board content = userdb.boardContentDatail(contentsIdx);
		String commentsList = userdb.CommentsDatail(contentsIdx);
		String CommentsNouserDetail = userdb.CommentsNouserDetail(contentsIdx);

		if (isSuccess == null) {
			model.addAttribute("userOradmin", "<a href=\"login\">로그인</a> <a href=\"signup\">회원가입</a>");
			model.addAttribute("commentsList", CommentsNouserDetail);
		} else {
			if (login_id.length() == 9 && login_id.split("_")[0].equals("admin")) { // 관리자 로그인
				model.addAttribute("userOradmin",
						"<a href=\"logout\">관리자 로그아웃</a>  <a href=\"admininfo_pwd\">관리자 정보 수정</a> <a href=\"userList\">회원목록 조회</a>");
				model.addAttribute("commentsList", commentsList);

			} else { // 유저 로그인
				model.addAttribute("userOradmin", "<a href=\"logout\">로그아웃</a><a href=\"myinfo_pwd\">개인정보수정</a>\n");
				model.addAttribute("commentsList", CommentsNouserDetail);
			}
		}
		model.addAttribute("idx", content.idx);
		model.addAttribute("user_title", content.user_title);
		model.addAttribute("user_id", content.user_id);
		model.addAttribute("created", content.created);
		model.addAttribute("user_content", content.user_content);
		return "boarddetail";
	}

	@RequestMapping(value = "/boardComments_action", method = RequestMethod.POST)
	public String boardCommentsAction(HttpServletRequest request, Locale locale, Model model) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		Boolean isSuccess = (Boolean) session.getAttribute("is_login");
		String login_id = (String) session.getAttribute("login_id");

		UserDB userdb = new UserDB();
		SignupUser user = new SignupUser(login_id);
		String user_id = "";

		if (isSuccess == null) {
			user_id = "noname";
		} else {
			if (login_id.length() == 9 && login_id.split("_")[0].equals("admin")) { // 관리자 로그인
				model.addAttribute("userOradmin",
						"<a href=\"logout\">관리자 로그아웃</a>  <a href=\"admininfo_pwd\">관리자 정보 수정</a> <a href=\"userList\">회원목록 조회</a>");
				user_id = "관리자";
			} else {
				user_id = login_id;
			}
		}
		String board_idx = request.getParameter("idx");
		String comments = request.getParameter("comments");

		// 첫번째created 시간을 불러오는 메소드 추가
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String now = sdf.format(Calendar.getInstance().getTime());

		Comments commentsAll = new Comments(user_id, comments, now, Integer.parseInt(board_idx));
		boolean insertSuccess = userdb.commentInsert(commentsAll);

		if (insertSuccess) {
			return "redirect:/boarddetail?idx=" + board_idx;
		} else
			model.addAttribute("m1", "작성 실패");
		return "message";
	}

	// 댓글 삭제
	@RequestMapping(value = "/deleteComments", method = RequestMethod.GET)
	public String deleteComments(HttpServletRequest request, Locale locale, Model model, @RequestParam("idx") int idx,
			@RequestParam("board_idx") int board_idx) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		HttpSession session = request.getSession();
		UserDB userdb = new UserDB();
		AdminDB gamedb = new AdminDB();

		Boolean isSuccess = (Boolean) session.getAttribute("is_login");
		String login_id = (String) session.getAttribute("login_id");

		model.addAttribute("userOradmin",
				"<a href=\"logout\">관리자 로그아웃</a>  <a href=\"admininfo_pwd\">관리자 정보 수정</a> <a href=\"userList\">회원목록 조회</a>");
		boolean deleteCheck = userdb.commentsDelete(idx);
		if (deleteCheck) {
			return "redirect:/boarddetail?idx=" + board_idx;
		} else
			model.addAttribute("m1", "삭제 오류");
		return "message";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String u(HttpServletRequest request, Locale locale, Model model, @RequestParam("idx") int contentsIdx,
			@RequestParam("user_id") String user_id) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		System.out.println(contentsIdx);
		System.out.println(user_id);

		HttpSession session = request.getSession();
		UserDB userdb = new UserDB();
		AdminDB gamedb = new AdminDB();

		Boolean isSuccess = (Boolean) session.getAttribute("is_login");
		String login_id = (String) session.getAttribute("login_id");
		Board content = userdb.boardContentDatail(contentsIdx);

		if (isSuccess == null) {
			model.addAttribute("userOradmin", "<a href=\"login\">로그인</a> <a href=\"signup\">회원가입</a>");
			model.addAttribute("m1", "로그인이 필요합니다");
			System.out.println("오류1");

			return "message";
		} else if (login_id.length() == 9 && login_id.split("_")[0].equals("admin")) { // 관리자 로그인
			System.out.println("오류2");

			model.addAttribute("userOradmin",
					"<a href=\"logout\">관리자 로그아웃</a>  <a href=\"admininfo_pwd\">관리자 정보 수정</a> <a href=\"userList\">회원목록 조회</a>");
			model.addAttribute("m1", "관리자의 경우 게시물 강제삭제만 가능합니다.");
			return "message";
		} else // 유저 로그인
			model.addAttribute("userOradmin", "<a href=\"logout\">로그아웃</a><a href=\"myinfo_pwd\">개인정보수정</a>\n");
		if (user_id.equals("'noname'") || user_id.equals("'관리자'")) {
			System.out.println("오류3");

			model.addAttribute("m1", "해당 글은 수정 및 삭제가 불가능합니다.");
			return "message";
		} else {
			System.out.println("오류4");

			session.setAttribute("contentsIdx", contentsIdx);
			session.setAttribute("contents_user_id", user_id);
			return "boardupdate_pwd_cnfm";
		}
	}

	@RequestMapping(value = "/update_confirm_action", method = RequestMethod.GET)
	public String updatePwdConfirm(HttpServletRequest request, Locale locale, Model model) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		UserDB db = new UserDB();

		String user_pwd = request.getParameter("pwd_confirm");
		user_pwd = db.sha256(user_pwd);

		String originpwd = (String) session.getAttribute("login_pwd");
		originpwd = db.sha256(originpwd);

		String contents_user_id = (String) session.getAttribute("contents_user_id");
		String login_id = (String) session.getAttribute("login_id");

		if (user_pwd.equals(originpwd) && contents_user_id.equals(login_id)) {
			model.addAttribute("userOradmin", "<a href=\"logout\">로그아웃</a><a href=\"myinfo_pwd\">개인정보수정</a>\n");
			int contentsIdx = (Integer) session.getAttribute("contentsIdx");

			Board board = db.boardContentDatail(contentsIdx);
			model.addAttribute("idx", board.idx);
			model.addAttribute("user_title", board.user_title);
			model.addAttribute("user_id", board.user_id);
			model.addAttribute("user_content", board.user_content);
			return "boardupdate";
		} else
			model.addAttribute("userOradmin", "<a href=\"logout\">로그아웃</a><a href=\"myinfo_pwd\">개인정보수정</a>\n");
		model.addAttribute("m1", "해당게시물 수정 및 삭제 권한이 없습니다");
		model.addAttribute("messageBtn", "<a href='boardList' class='goGameBtn'>retun boardList</a>");
		return "message";
	}

	@RequestMapping(value = "/boardupdate_action", method = RequestMethod.POST)
	public String boardupdateAction(HttpServletRequest request, Locale locale, Model model,
			@RequestParam("idx") int contentIdx, @RequestParam("user_title") String user_title,
			@RequestParam("user_id") String user_id, @RequestParam("user_content") String user_content) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		Boolean isSuccess = (Boolean) session.getAttribute("is_login");
		String login_id = (String) session.getAttribute("login_id");

		UserDB userdb = new UserDB();
		SignupUser user = new SignupUser(login_id);

		model.addAttribute("userOradmin", "<a href=\"logout\">로그아웃</a><a href=\"myinfo_pwd\">개인정보수정</a>\n");
		Board board = new Board(user_title, user_id, user_content, contentIdx);
		boolean updateSuccess = userdb.boardUpdate(board);

		if (updateSuccess) {
			model.addAttribute("m1", "update 성공");
			return "message";
		} else
			model.addAttribute("m1", "update 실패");
		return "message";
	}

	// 게시물 삭제
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deletecontents(HttpServletRequest request, Locale locale, Model model,
			@RequestParam("idx") int contentsIdx, @RequestParam("user_id") String user_id) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		HttpSession session = request.getSession();
		UserDB userdb = new UserDB();
		AdminDB gamedb = new AdminDB();

		Boolean isSuccess = (Boolean) session.getAttribute("is_login");
		String login_id = (String) session.getAttribute("login_id");

		if (isSuccess == null) {
			model.addAttribute("userOradmin", "<a href=\"login\">로그인</a> <a href=\"signup\">회원가입</a>");
			model.addAttribute("m1", "로그인이 필요합니다");
			return "message";
		} else if (login_id.length() == 9 && login_id.split("_")[0].equals("admin")) { // 관리자 로그인
			model.addAttribute("userOradmin",
					"<a href=\"logout\">관리자 로그아웃</a>  <a href=\"admininfo_pwd\">관리자 정보 수정</a> <a href=\"userList\">회원목록 조회</a>");
			boolean deleteCheck = userdb.boardDelete(contentsIdx);
			model.addAttribute("m1", "관리자 권한으로 삭제 완료");
			return "message";
		} else { // 유저 로그인
			if (user_id.equals("noname") || user_id.equals("'관리자'")) {
				model.addAttribute("userOradmin", "<a href=\"logout\">로그아웃</a><a href=\"myinfo_pwd\">개인정보수정</a>\n");
				model.addAttribute("m1", "해당게시물 삭제 불가");
				return "message";
			} else
				model.addAttribute("userOradmin", "<a href=\"logout\">로그아웃</a><a href=\"myinfo_pwd\">개인정보수정</a>\n");
			session.setAttribute("contents_idx", contentsIdx);
			session.setAttribute("contents_user_id", user_id);
			return "content_pwd_confirm";
		}
	}

	@RequestMapping(value = "/content_confirm_action", method = RequestMethod.GET)
	public String contentPwdConfirm(HttpServletRequest request, Locale locale, Model model) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		UserDB db = new UserDB();
		String user_pwd = request.getParameter("pwd_confirm");
//		user_pwd = db.sha256(user_pwd);
		HttpSession session = request.getSession();
		String originpwd = (String) session.getAttribute("login_pwd");
		int contentsIdx = (Integer) session.getAttribute("contents_idx");
		String contents_user_id = (String) session.getAttribute("contents_user_id");
		String login_id = (String) session.getAttribute("login_id");

		if (user_pwd.equals(originpwd) && contents_user_id.equals(login_id)) {
			boolean isSuccess = db.boardDelete(contentsIdx);

			if (!isSuccess) {
				model.addAttribute("userOradmin", "<a href=\"logout\">로그아웃</a><a href=\"myinfo_pwd\">개인정보수정</a>\n");
				model.addAttribute("m1", "게시물 삭제에 실패했습니다");
				return "message";
			} else
				model.addAttribute("userOradmin", "<a href=\"logout\">로그아웃</a><a href=\"myinfo_pwd\">개인정보수정</a>\n");
			model.addAttribute("m1", "삭제성공");
			return "message";
		}
		model.addAttribute("userOradmin", "<a href=\"logout\">로그아웃</a><a href=\"myinfo_pwd\">개인정보수정</a>\n");
		model.addAttribute("m1", "게시물 삭제 권한이 없습니다");
		return "message";
	}

	// Q&A 리스트 조회
	@RequestMapping(value = "/questionList", method = RequestMethod.GET)
	public String userquestionList(HttpServletRequest request, Locale locale, Model model) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		Boolean isSuccess = (Boolean) session.getAttribute("is_login");
		String login_id = (String) session.getAttribute("login_id");

		UserDB userdb = new UserDB();
		AdminDB admindb = new AdminDB();
		SignupUser user = new SignupUser(login_id);
		String user_id = "";

		String htmlString1 = userdb.userquestionList(user);
		String htmlString2 = admindb.selectQuestion();

		if (isSuccess == null) {
			model.addAttribute("userOradmin", "<a href=\"login\">로그인</a> <a href=\"signup\">회원가입</a>");
			model.addAttribute("m1", "로그인 후 이용해주세요.");
			return "message";
		} else if (login_id.length() == 9 && login_id.split("_")[0].equals("admin")) {
			model.addAttribute("userOradmin",
					"<a href=\"logout\">관리자 로그아웃</a>  <a href=\"admininfo_pwd\">관리자 정보 수정</a> <a href=\"userList\">회원목록 조회</a>");
			model.addAttribute("onlyUser", "<a href='answerYes'>답변완료</a><a href='answerNo'>답변미완료</a>");
			model.addAttribute("question_admin", htmlString2);
			return "questionList";
		} else
			model.addAttribute("userOradmin", "<a href=\"logout\">로그아웃</a><a href=\"myinfo_pwd\">개인정보수정</a>\n");
		model.addAttribute("onlyUser", "<a href=\"questionInsert\">글쓰기</a>\n");
		model.addAttribute("question_user", htmlString1);
		return "questionList";
	}

	// Q&A 리스트 조회 - 답변 완료
	@RequestMapping(value = "/answerYes", method = RequestMethod.GET)
	public String answeryes(HttpServletRequest request, Locale locale, Model model) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		Boolean isSuccess = (Boolean) session.getAttribute("is_login");
		String login_id = (String) session.getAttribute("login_id");

		UserDB userdb = new UserDB();
		AdminDB admindb = new AdminDB();
		SignupUser user = new SignupUser(login_id);
		String user_id = "";

		String htmlString1 = userdb.userquestionList(user);
		String htmlString2 = admindb.answerYes();

		if (isSuccess == null) {
			model.addAttribute("userOradmin", "<a href=\"login\">로그인</a> <a href=\"signup\">회원가입</a>");
			model.addAttribute("m1", "로그인 후 이용해주세요.");
			return "message";
		} else if (login_id.length() == 9 && login_id.split("_")[0].equals("admin")) {
			model.addAttribute("userOradmin",
					"<a href=\"logout\">관리자 로그아웃</a>  <a href=\"admininfo_pwd\">관리자 정보 수정</a> <a href=\"userList\">회원목록 조회</a>");
			model.addAttribute("onlyUser",
					"<a href='questionList'>질문리스트</a><a href='answerYes'>답변완료</a><a href='answerNo'>답변미완료</a>");
			model.addAttribute("question_admin", htmlString2);
			return "questionList";
		} else
			model.addAttribute("userOradmin", "<a href=\"logout\">로그아웃</a><a href=\"myinfo_pwd\">개인정보수정</a>\n");
		model.addAttribute("onlyUser", "<a href=\"questionInsert\">글쓰기</a>\n");
		model.addAttribute("question_user", htmlString1);
		return "questionList";
	}

	// Q&A 리스트 조회_ 답변 미완료
	@RequestMapping(value = "/answerNo", method = RequestMethod.GET)
	public String answerno(HttpServletRequest request, Locale locale, Model model) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		Boolean isSuccess = (Boolean) session.getAttribute("is_login");
		String login_id = (String) session.getAttribute("login_id");

		UserDB userdb = new UserDB();
		AdminDB admindb = new AdminDB();
		SignupUser user = new SignupUser(login_id);
		String user_id = "";

		String htmlString1 = userdb.userquestionList(user);
		String htmlString2 = admindb.answerNo();

		if (isSuccess == null) {
			model.addAttribute("userOradmin", "<a href=\"login\">로그인</a> <a href=\"signup\">회원가입</a>");
			model.addAttribute("m1", "로그인 후 이용해주세요.");
			return "message";
		} else if (login_id.length() == 9 && login_id.split("_")[0].equals("admin")) {
			model.addAttribute("userOradmin",
					"<a href=\"logout\">관리자 로그아웃</a>  <a href=\"admininfo_pwd\">관리자 정보 수정</a> <a href=\"userList\">회원목록 조회</a>");
			model.addAttribute("onlyUser",
					"<a href='questionList'>질문리스트</a><a href='answerYes'>답변완료</a><a href='answerNo'>답변미완료</a>");
			model.addAttribute("question_admin", htmlString2);
			return "questionList";
		} else
			model.addAttribute("userOradmin", "<a href=\"logout\">로그아웃</a><a href=\"myinfo_pwd\">개인정보수정</a>\n");
		model.addAttribute("onlyUser", "<a href=\"questionInsert\">글쓰기</a>\n");
		model.addAttribute("question_user", htmlString1);
		return "questionList";
	}

	// 로그인 유저 Q&A 작성
	@RequestMapping(value = "/questionInsert", method = RequestMethod.GET)
	public String userquestionInsert(HttpServletRequest request, Locale locale, Model model) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		Boolean isSuccess = (Boolean) session.getAttribute("is_login");
		String login_id = (String) session.getAttribute("login_id");
		UserDB userdb = new UserDB();

		if (isSuccess) {
			model.addAttribute("userOradmin", "<a href=\"logout\">로그아웃</a><a href=\"myinfo_pwd\">개인정보수정</a>\n");
			model.addAttribute("user_id", login_id);
			model.addAttribute("answer", "관리자가 곧 답변할 예정입니다. 잠시 기다려주세요.");

			return "questionInsert";
		}
		return "questionInsert";
	}

	@RequestMapping(value = "/questionInsert_action", method = RequestMethod.POST)
	public String questioninsertAction(HttpServletRequest request, Locale locale, Model model) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		UserDB userdb = new UserDB();
		Boolean isSuccess = (Boolean) session.getAttribute("is_login");
		String login_id = (String) session.getAttribute("login_id");

		SignupUser user = new SignupUser(login_id);
		String question_title = request.getParameter("question_title");
		String question_content = request.getParameter("question_content");

		model.addAttribute("userOradmin", "<a href=\"logout\">로그아웃</a><a href=\"myinfo_pwd\">개인정보수정</a>\n");
		model.addAttribute("user_id", login_id);

		// 첫번째created 시간을 불러오는 메소드 추가
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String now = sdf.format(Calendar.getInstance().getTime());
		Question question = new Question(question_title, login_id, question_content, now);
		boolean insertSuccess = userdb.questionInsert(question);
		if (insertSuccess) {
			model.addAttribute("m1", "작성성공");
			return "message";
		} else
			model.addAttribute("m1", "작성 실패");

		return "message";
	}

	// 로그인 유저 - Q&A 목록에서 하나 클릭시 게시물 조회 페이지로 이동
	@RequestMapping(value = "/userquestiondetail", method = RequestMethod.GET)
	public String userquestionDetail(HttpServletRequest request, Locale locale, Model model,
			@RequestParam("idx") int contentsIdx) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		HttpSession session = request.getSession();
		UserDB userdb = new UserDB();
		AdminDB admindb = new AdminDB();

		Boolean isSuccess = (Boolean) session.getAttribute("is_login");
		String login_id = (String) session.getAttribute("login_id");
		Question content = userdb.userquestionDetail(contentsIdx);

		if (isSuccess == null) {
			model.addAttribute("userOradmin", "<a href=\"login\">로그인</a> <a href=\"signup\">회원가입</a>");
		} else {
			if (login_id.length() == 9 && login_id.split("_")[0].equals("admin")) { // 관리자 로그인
				model.addAttribute("userOradmin",
						"<a href=\"logout\">관리자 로그아웃</a>  <a href=\"admininfo_pwd\">관리자 정보 수정</a> <a href=\"userList\">회원목록 조회</a>");
				model.addAttribute("adminOnly", "<a href='questionupdate?idx=" + contentsIdx + "'>답변하기</a>");
			} else { // 유저 로그인
				model.addAttribute("userOradmin", "<a href=\"logout\">로그아웃</a><a href=\"myinfo_pwd\">개인정보수정</a>\n");
				model.addAttribute("adminOnly",
						"<a href='questiondelete?user_id=" + login_id + "&idx=" + contentsIdx + "'>삭제하기</a>");
			}
		}
		model.addAttribute("idx", content.idx);
		model.addAttribute("question_title", content.question_title);
		model.addAttribute("user_id", content.user_id);
		model.addAttribute("created", content.created);
		model.addAttribute("question_content", content.question_content);
		model.addAttribute("answer", content.answer);
		return "questionDetail";
	}

	// Q&A 답변하기
	@RequestMapping(value = "/questionupdate", method = RequestMethod.GET)
	public String questionupdate(HttpServletRequest request, Locale locale, Model model,
			@RequestParam("idx") int contentsIdx) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		HttpSession session = request.getSession();
		UserDB userdb = new UserDB();

		Boolean isSuccess = (Boolean) session.getAttribute("is_login");
		String login_id = (String) session.getAttribute("login_id");
		Question question = userdb.userquestionDetail(contentsIdx);
		model.addAttribute("userOradmin",
				"<a href=\"logout\">관리자 로그아웃</a>  <a href=\"admininfo_pwd\">관리자 정보 수정</a> <a href=\"userList\">회원목록 조회</a>");

		model.addAttribute("idx", question.idx);
		model.addAttribute("question_title", question.question_title);
		model.addAttribute("user_id", question.user_id);
		model.addAttribute("question_content", question.question_content);
		model.addAttribute("created", question.created);
		model.addAttribute("answer", question.answer);
		return "questionUpdate";
	}

	@RequestMapping(value = "/questionupdate_action", method = RequestMethod.POST)
	public String questionupdateAction(HttpServletRequest request, Locale locale, Model model) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		Boolean isSuccess = (Boolean) session.getAttribute("is_login");
		String login_id = (String) session.getAttribute("login_id");

		AdminDB admindb = new AdminDB();
		UserDB userdb = new UserDB();

		String intIdx = request.getParameter("idx");
		int idx = Integer.parseInt(intIdx);
		String question_title = request.getParameter("question_title");
		String user_id = request.getParameter("user_id");
		String question_content = request.getParameter("question_content");
		String answer = request.getParameter("answer");

		Question question = new Question(question_title, user_id, question_content, answer, idx);
		boolean updateSuccess = admindb.questionUpdate(question);

		if (updateSuccess) {
			model.addAttribute("userOradmin",
					"<a href=\"logout\">관리자 로그아웃</a>  <a href=\"admininfo_pwd\">관리자 정보 수정</a> <a href=\"userList\">회원목록 조회</a>");

//			model.addAttribute("m1", "update 성공");
//			return "questionUpdate?idx="+idx;
			return "redirect:/questionList";
		} else {
			model.addAttribute("m1", "update 실패");
			model.addAttribute("userOradmin",
					"<a href=\"logout\">관리자 로그아웃</a>  <a href=\"admininfo_pwd\">관리자 정보 수정</a> <a href=\"userList\">회원목록 조회</a>");
			return "message";
		}
	}

	// 로그인 유저 - Q&A 삭제
	@RequestMapping(value = "/questiondelete", method = RequestMethod.GET)
	public String deletequestion(HttpServletRequest request, Locale locale, Model model,
			@RequestParam("idx") int contentsIdx, @RequestParam("user_id") String user_id) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		HttpSession session = request.getSession();
		UserDB userdb = new UserDB();
		AdminDB admindb = new AdminDB();

		Boolean isSuccess = (Boolean) session.getAttribute("is_login");
		String login_id = (String) session.getAttribute("login_id");

		boolean deletesuccess = userdb.questionDelete(contentsIdx);
		if (deletesuccess) {
			model.addAttribute("userOradmin", "<a href=\"logout\">로그아웃</a><a href=\"myinfo_pwd\">개인정보수정</a>\n");
			model.addAttribute("m1", "삭제 완료");

		} else
			model.addAttribute("m1", "삭제실패");
		model.addAttribute("userOradmin", "<a href=\"logout\">로그아웃</a><a href=\"myinfo_pwd\">개인정보수정</a>\n");

		return "message";
	}

	// 게임실행
	// 랭킹 페이지
	@RequestMapping(value = "/ranking", method = RequestMethod.GET)
	public String ranking(HttpServletRequest request, Locale locale, Model model) {

		HttpSession session = request.getSession();
		GameDB db = new GameDB();
		String ranking123 = db.rank123Data();
		String htmlString = db.rankData();

		Boolean is_login = (Boolean) session.getAttribute("is_login");
		String login_id = (String) session.getAttribute("login_id");

		if (is_login == null) {
			model.addAttribute("userOradmin", "<a href=\"login\">로그인</a> <a href=\"signup\">회원가입</a>");
			model.addAttribute("ranking123", ranking123);
			model.addAttribute("rankList", htmlString);
			return "ranking";
		} else if (login_id.length() == 9 && login_id.split("_")[0].equals("admin")) {
			model.addAttribute("userOradmin",
					"<a href=\"logout\">관리자 로그아웃</a>  <a href=\"admininfo_pwd\">관리자 정보 수정</a> <a href=\"userList\">회원목록 조회</a>");
			model.addAttribute("m1", login_id + "님의 관리자모드");
			model.addAttribute("ranking123", ranking123);
			model.addAttribute("rankList", htmlString);
			return "ranking";
		} else
			model.addAttribute("userOradmin", "<a href=\"logout\">로그아웃</a><a href=\"myinfo_pwd\">개인정보수정</a>\n");
		model.addAttribute("ranking123", ranking123);
		model.addAttribute("rankList", htmlString);
		return "ranking";
	}

	// 비회원용 게임-earth ver (score 저장 X)
	@RequestMapping(value = "/game_NoScore", method = RequestMethod.GET)
	public String game_NoScore(Locale locale, Model model) {
		try {
			String line;
			InputStream is;
			is = Runtime.getRuntime()
					.exec("python C:\\Users\\SMART-14\\Desktop\\team7Project\\MyFirstProjectGame/OaiaNoScore.py")
					.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "MS949"));
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			br.close();
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "redirect:/";
	}

	// 관리자 게임실행-earth ver  (score 저장됨)
	@RequestMapping(value = "/game_adminScore", method = RequestMethod.GET)
	public String admin_game(HttpServletRequest request, Locale locale, Model model) {
		HttpSession session = request.getSession();
		String adminId = (String) session.getAttribute("login_id");
		AdminDB db = new AdminDB();
		String isSuccess = db.adminScore(adminId);
		try {
			String line;
			InputStream is;
			is = Runtime.getRuntime()
					.exec("python C:\\Users\\SMART-14\\Desktop\\team7Project\\MyFirstProjectGame/Oaia.py " + isSuccess)
					.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "MS949"));
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			br.close();
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "redirect:/";
	}

	// 유저 게임실행-earth ver  (score 저장됨)
	@RequestMapping(value = "/game_userScore", method = RequestMethod.GET)
	public String user_game(HttpServletRequest request, Locale locale, Model model) {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("login_id");
		UserDB db = new UserDB();
		String isSuccess = db.userScore(userId);
		try {
			String line;
			InputStream is;
			is = Runtime.getRuntime()
					.exec("python C:\\Users\\SMART-14\\Desktop\\team7Project\\MyFirstProjectGame/Oaia.py " + isSuccess)
					.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "MS949"));
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			br.close();
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "redirect:/";
	}

	// 비회원용 게임-space ver (score 저장 X)
		@RequestMapping(value = "/game_NoScore_space", method = RequestMethod.GET)
		public String game_NoScore_space(Locale locale, Model model) {
			try {
				String line;
				InputStream is;
				is = Runtime.getRuntime()
						.exec("python C:\\Users\\SMART-14\\Desktop\\team7Project\\MyFirstProjectGame/OaiaNoScoreSpace.py")
						.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is, "MS949"));
				while ((line = br.readLine()) != null) {
					System.out.println(line);
				}
				br.close();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return "redirect:/";
		}

		// 관리자 게임실행-space ver  (score 저장됨)
		@RequestMapping(value = "/game_adminScore_space", method = RequestMethod.GET)
		public String admin_game_space(HttpServletRequest request, Locale locale, Model model) {
			HttpSession session = request.getSession();
			String adminId = (String) session.getAttribute("login_id");
			AdminDB db = new AdminDB();
			String isSuccess = db.adminScore(adminId);
			try {
				String line;
				InputStream is;
				is = Runtime.getRuntime()
						.exec("python C:\\Users\\SMART-14\\Desktop\\team7Project\\MyFirstProjectGame/OaiaSpace.py " + isSuccess)
						.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is, "MS949"));
				while ((line = br.readLine()) != null) {
					System.out.println(line);
				}
				br.close();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return "redirect:/";
		}

		// 유저 게임실행-space ver  (score 저장됨)
		@RequestMapping(value = "/game_userScore_space", method = RequestMethod.GET)
		public String user_game_space(HttpServletRequest request, Locale locale, Model model) {
			HttpSession session = request.getSession();
			String userId = (String) session.getAttribute("login_id");
			UserDB db = new UserDB();
			String isSuccess = db.userScore(userId);
			try {
				String line;
				InputStream is;
				is = Runtime.getRuntime()
						.exec("python C:\\Users\\SMART-14\\Desktop\\team7Project\\MyFirstProjectGame/OaiaSpace.py " + isSuccess)
						.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is, "MS949"));
				while ((line = br.readLine()) != null) {
					System.out.println(line);
				}
				br.close();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return "redirect:/";
		}
	// contactus부분
	@RequestMapping(value = "/contactus", method = RequestMethod.GET)
	public String contactus(HttpServletRequest request, Locale locale, Model model) {
		HttpSession session = request.getSession();

		Boolean is_login = (Boolean) session.getAttribute("is_login");
		String login_id = (String) session.getAttribute("login_id");

		if (is_login == null) {
			model.addAttribute("userOradmin", "<a href=\"login\">로그인</a> <a href=\"signup\">회원가입</a>");
			return "contactus";
		} else if (login_id.length() == 9 && login_id.split("_")[0].equals("admin")) {
			model.addAttribute("userOradmin",
					"<a href=\"logout\">관리자 로그아웃</a>  <a href=\"admininfo_pwd\">관리자 정보 수정</a> <a href=\"userList\">회원목록 조회</a>");
			return "contactus";
		} else
			model.addAttribute("userOradmin", "<a href=\"logout\">로그아웃</a><a href=\"myinfo_pwd\">개인정보수정</a>\n");
		return "contactus";
	}

	// contactus부분
	@RequestMapping(value = "/Aboutus", method = RequestMethod.GET)
	public String Aboutus(HttpServletRequest request, Locale locale, Model model) {
		HttpSession session = request.getSession();

		Boolean is_login = (Boolean) session.getAttribute("is_login");
		String login_id = (String) session.getAttribute("login_id");

		if (is_login == null) {
			model.addAttribute("userOradmin", "<a href=\"login\">로그인</a> <a href=\"signup\">회원가입</a>");
			return "Aboutus";
		} else if (login_id.length() == 9 && login_id.split("_")[0].equals("admin")) {
			model.addAttribute("userOradmin",
					"<a href=\"logout\">관리자 로그아웃</a>  <a href=\"admininfo_pwd\">관리자 정보 수정</a> <a href=\"userList\">회원목록 조회</a>");
			return "Aboutus";
		} else
			model.addAttribute("userOradmin", "<a href=\"logout\">로그아웃</a><a href=\"myinfo_pwd\">개인정보수정</a>\n");
		return "Aboutus";
	}
}
