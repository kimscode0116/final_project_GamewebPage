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
	href="./resources/boardList.css">



<!--CDN 링크 -->
<!-- Bootstrap CSS -->
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous"> -->
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
					<li><a href="question">Q&A</a></li>
				</span>
			</ul>
		</nav>
		<div class="dropdown">
			<button class="dropbtn">
				<a href="#"><i class="fas fa-bars"></i></a>
			</button>
			<div class="dropdown-content">
				${userOradmin} <a href="createDB">DB생성</a>
			</div>
		</div>
	</header>
	<main>
		<div class="wrapper">
			<span class="mainTitle"> <a href="boardList"> <img
					src="./resources/image/talktoTalk_title2.png"></a>
			</span>
			<div class="content">
				<!-- <div class="officialMemo">
				<h2>* 관리자 업데이트 소식</h2>
				</div> -->
				<section class="wrap">
					<table class="sub_news" border="1" cellspacing="0">
						<caption>
							<a>Talk to talk</a>
						</caption>
						<!-- 		<colgroup>
							<col width="10%" />
							<col width="45%" />
							<col width="20%" />
							<col width="20%" />
						</colgroup> -->
						<thead>
							<tr>
								<th class="idx">글번호</th>
								<th>제목</th>
								<th class="name">글쓴이</th>
								<th class="date">작성일</th>
							</tr>
						</thead>
						<%-- <tbody>${boardList }
						</tbody> --%>
						<tbody>
							<tr>
								<td class="idx">1</td>
								<td class="title"><a href="#">왜 저만 게임에서 지는건가요?ㅜㅜ아니 다
										부시고싶네요</a></td>
								<td class="name">김글쓴이</td>
								<td class="date">2021/07/01</td>
							</tr>
							<tr>
								<td class="idx">2</td>
								<td class="title"><a href="#">아이템 샀는데 ㅡㅡ 이거 구닥이네요
										구매하지말고 차라리 다른거 구매해요;;; 돈아까워 죽겠음 그리고 계정 주 김글쓴이 사기꾼</a></td>
								<td class="name">박글쓴이</td>
								<td class="date">2021/07/01</td>
							</tr>
							<tr>
								<td class="idx">3</td>
								<td class="title"><a href="#">아이템 샀는데 ㅡㅡ 이거 구닥이네요
										구매하지말고 차라리 다른거 구매해요;;; 돈아까워 죽겠음 그리고 계정 주 김글쓴이 사기꾼</a></td>
								<td class="name">최글쓴이</td>
								<td class="date">2021/07/01</td>
							</tr>
							<tr>
								<td class="idx">3</td>
								<td class="title"><a href="#">아이템 샀는데 ㅡㅡ 이거 구닥이네요
										구매하지말고 차라리 다른거 구매해요;;; 돈아까워 죽겠음 그리고 계정 주 김글쓴이 사기꾼</a></td>
								<td class="name">최글쓴이</td>
								<td class="date">2021/07/01</td>
							</tr>
							<tr>
								<td class="idx">3</td>
								<td class="title"><a href="#">아이템 샀는데 ㅡㅡ 이거 구닥이네요
										구매하지말고 차라리 다른거 구매해요;;; 돈아까워 죽겠음 그리고 계정 주 김글쓴이 사기꾼</a></td>
								<td class="name">최글쓴이</td>
								<td class="date">2021/07/01</td>
							</tr>
						</tbody>
					</table>
					<tr >
						<td class="insertBtn"><div class="boardInsertBtn"> <a
								href="boardInsert">글쓰기</a></div></td>
					</tr>
					<tr>
						<td colspan="6">
							<center>
								<a href="#">1</a> <a href="#">2</a> <a href="#">3</a> <a
									href="#">4</a> <a href="#">다음▶</a>
							</center>
						</td>
					</tr>
				</section>
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
					<a class="link-1" href="#">Home</a> <a href="#">Blog</a> <a
						href="#">About</a> <a href="#">Contact Us</a>
				</p>
			</div>
			<div class="footer-right">

				<a href="#"><img src="./resources/image/fb.png"></a> <a
					href="#"><img src="./resources/image/is.png"></a> <a href="#"><img
					src="./resources/image/GitHub_Logo.png"></a> <a href="#"><img
					src="./resources/image/mail.png"></a>

			</div>
			<div class="snsLogo">
				<p>OaiaGameCompany &copy; 2021</p>
			</div>
		</div>
	</footer>
</body>
</html>
