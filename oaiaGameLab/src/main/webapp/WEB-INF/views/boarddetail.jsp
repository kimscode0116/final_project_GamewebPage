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
	href="./resources/boarddetail.css">

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
				${userOradmin} 
				<!-- <a href="createDB">DB생성</a> -->
			</div>
		</div>
	</header>
	<main>
		<div class="wrapper">
			<span class="mainTitle"> <a href="boardList"> <img
					src="./resources/image/talktoTalk_title2.png"></a>
			</span>
			<div class="insertBtn">
				<div class="boardInsertBtn">
					<a href="update?idx=${idx}&user_id=${user_id}">수정하기</a>
					<a href="delete?user_id=${user_id}&idx=${idx}">삭제하기</a>
				</div>
			</div>
		</div>
		<div class="content">
			<section class="wrap">
				<div class="btnArea">
					<table class="contents">
						<caption>
							<i class="fas fa-fire-alt"></br></i> <a>${user_title}</span></a>
						</caption>
						<tbody>
							<tr class="idx">
								<td><span type="text" name="idx" value="${idx}"><i
										class="fas fa-circle"> ${idx }</span></td>
							</tr>
							<tr class="nickname">
								<td><span type="text" name="user_id"
									value="${user_id}"><i class="fas fa-ghost">
											${user_id}</span></td>
							</tr>
							<tr class="created">
								<td><span type="text" name="created" value="${created}"><i
										class="fas fa-clock"> ${created}</span></td>
							</tr>
							<tr class="bottom">
								<td><span type="text" name="user_content"
									value="${user_content}">${user_content}</span></td>
							</tr>
						</tbody>
					</table>
			</section>
			<div class="comments">
				<table class="contents_comment">
					<tbody>
						${commentsList }
						<%-- <tr class="nickname">
							<td><span type="text" name="user_nickname" ><i class="fas fa-ghost"></i>
										${user_nickname} : 댓글 내용이 뭐지 댓글 내용이 뭐지 뭐지뭐지뭐지 댓글 내용이 뭐지 댓글 내용이 뭐지 </span><a href="#">   댓글삭제</a></td>
						</tr> --%>
					</tbody>
				</table>
				<form action="boardComments_action" method="POST" class="totalForm">
					<div class="comments_by_user">
						<hr>
						<input type="hidden" name="idx" value="${idx}"> <input
							name="comments" placeholder="댓글을 입력하세요"></input> <input
							type="submit" value="update" class="submit_btn"></span>
					</div>
				</form>
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
