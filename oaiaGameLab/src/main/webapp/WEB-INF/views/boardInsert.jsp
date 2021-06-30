<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>Board Insert</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel='stylesheet' type='text/css' media='screen' href='${pageContext.request.contextPath}/resources/insert.css'>
</head>
<body>
    <section class="wrap">
    <h1>Board Insert</h1>
        <form action="boardinsert_action" method="post">
            <label> TITLE
                <input type="text" name="user_title" placeholder="Title" />
            <br> 
            </label>
            <label> 작성자
                <input type="text" name="user_id" value="${user_id}" readonly>
            <br> 
            </label> 
            <label> CONTENT  
            <br> 
                <textarea name="user_content" cols="50" rows="10" placeholder="내용을 입력하세요"></textarea>
            </label>
            <input type="submit" value="글쓰기">
        </form>
        
    </section>
</body>
</html>