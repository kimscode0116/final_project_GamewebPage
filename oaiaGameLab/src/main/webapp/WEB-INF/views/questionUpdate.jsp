<!-- <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%> -->
<html>

<head>
<meta charset="utf-8">
<title>Main</title>
<meta name="viewport" content="width-device-width, initial-scale=1">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css"
	rel="stylesheet">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" type="text/css" media="screen"
	href="./resources/questiondetail.css">
</head>

<body>
	<header>
		<span class="logo"> <a href="/oaiaGameLab/"><img
				src="./resources/image/mainLogo2.png"></a>
		</span>
		<nav>
			<ul class="menubar">
				<span>
					<li><a href="ranking">Ranking</a></li>
				</span>
				<span>
					<li><a href="boardList">TalktoTalk</a></li>
				</span>
				<span>
					<li><a href="questionList">Q&A</a></li>
				</span>
			</ul>
		</nav>
		<div class="dropdown">
			<button class="dropbtn">
				<a href="#"><i class="fas fa-bars"></i></a>
			</button>
			<div class="dropdown-content">
				${userOradmin} <!-- <a href="createDB">DB생성</a> -->
			</div>
		</div>
	</header>
	<main>
		<div class="wrapper">
			<span class="mainTitle"> <a href="boardList"> <img
					src="./resources/image/talktoTalk_title2.png"></a>
			</span>
			<div class="content">
				<section class="wrap">
					<table class="contents">
						<tbody>
							<tr class="upper">
								<th>글번호</th>

								<td><input type="hidden" name="idx" value="${idx }"
									class="default" readonly>${idx }</input></td>
							</tr>
							<tr class="upper">
								<th>제목</th>
								<td><input type="hidden" name="question_title"
									value="${question_title}" class="default" readonly>${question_title}</input></td>
							</tr>
							<tr class="upper">
								<th>작성자</th>
								<td><input type="hidden" name="user_id" value="${user_id}"
									class="default" readonly>${user_id}</input></td>

							</tr>
							<tr class="upper">
								<th>작성시간</th>
								<td><input type="hidden" name="created" value="${created}"
									class="default" readonly>${created}</input></td>
							</tr>
							<tr class="bottom">
								<th>내용</th>
								<td><input type="hidden" name="question_content"
									value="${question_content}" class="default" readonly>${question_content}</input></td>
							</tr>
						</tbody>
					</table>
				</section>
				<form action="questionupdate_action" method="POST" class="totalForm">
					<div class="adminAnswer">
						<caption>
							<div class="adminTitle">
								<h2>
									<i class="fas fa-angle-double-left"></i>문의 내용 답변<i
										class="fas fa-angle-double-right"></i>
								</h2>
							</div>
						</caption>
						<form action="questionupdate_action" method="POST" class="totalForm">
							<input type="hidden" name="idx" value="${idx }" class="default"
								readonly>${idx }</input>
							<div class="admintContent">
								<span name="created"> <textarea name="answer" value="${answer}" placeholder="관리자 답변란 입니다.">${answer}</textarea>
								</span>
							</div>
					</div>
			</div>
			<div class="btnArea">
				<div class="insertBtn">
					<div class="boardInsertBtn">
						<input type="submit" value="update" class="submit_btn">
					</form>
						
					</div>
				</div>
			</div>
		</div>
	</main>
	<footer class="footer-distributed">
		<div class="bottomWrap">
			<div class="bottomLogo">
				<img src="./resources/image/bottomLogo.gif">
			</div>
			<div class="footer-left">
				<p class="footer-links">
					<a class="link-1" href="#">Home</a> <a href="Aboutus">About</a> <a href="contactus">Contact Us</a>

				</p>
			</div>
			<div class="footer-right">

				<a href="https://www.instagram.com/kkeehhh"><img
					src="./resources/image/is.png"></a> <a
					href="https://www.instagram.com/go_soojin_"><img
					src="./resources/image/is2.png"></a> <a
					href="https://github.com/0seony"><img
					src="./resources/image/GitHub_Logo.png"></a> <a
					href="https://github.com/dudpray0220"><img
					src="./resources/image/GitHub_Logo2.png"></a>

			</div>
			<div class="snsLogo">
				<p>OaiaGameCompany &copy; 2021</p>
			</div>
		</div>
	</footer>
</body>
</html>
