<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>USER LIST</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel='stylesheet' type='text/css' media='screen' href='${pageContext.request.contextPath}/resources/list.css'>
</head>
<body>
    <section class="wrap">
        <table>
            <thead>
                <tr>
                    <th>IDX</th><th>ID</th><th>NAME</th><th>BIRTHDAY</th><th>EMAIL</th><th>NICKNAME</th><th>JOINED DATE</th>
                </tr>
            </thead>
            <tbody>
				${userList }
            </tbody>
        </table>
        <br>
    </section>
    
</body>
</html>