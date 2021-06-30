<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>Board LIST</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel='stylesheet' type='text/css' media='screen' href='${pageContext.request.contextPath}/resources/list.css'>
</head>
<body>
    <section class="wrap">
        <table>
            <thead>
                <tr>
                    <th>IDX</th><th>TITLE</th><th>CONTENT</th><th>CREATED DATE</th>
                </tr>
            </thead>
            <tbody>
				${boardList }
            </tbody>
        </table>
        <br>
        <a href="boardInsert" style="margin-top: 50px;">글쓰기</a>
        <br>
        <a href="/oaiaGameLab" style="margin-top: 50px;">홈으로</a>
    </section>
    
</body>
</html>