<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="UTF-8">
	<title>科目登録完了</title>
	<style>
		body {font-family: sans-serif; margin: 40px; line-height: 1.6; text-align: center; }
		.container { max-width: 500px; margin: auto; border: 1px solid #ccc; padding: 30px; border-radius: 8px; background-color: #fff; }
		h2 { color: #333; margin: -bottom: 20px; }
		.done-message { font-size: 18px; color: #28a745; font-weight: bold; margin-bottom: 30px; }
		.link-group { display: flex; flex-direction: column; gap: 15px; align-items: center;}
		.nav-link { text-decoration: none; color: #007bff; font-size: 16px; border: 1px solid #007bff; padding: 10px 20px; border-radius: 4px; width: 200px; transition: 0.3; }
		.nav-link:hover { back-ground-color: #007bff; color: #fff; }
	</style>
</head>
<body>

<div class="container">
	<h2>科目登録完了</h2>
	<p class="done-message">科目の登録が完了しました。</p>
	<div class="link-group">
		<a href="subject_create.jsp" class="nav-link">科目登録画面に遷移する</a>
		<a href="SubjectListServlet" class="nav-link">科目管理一覧画面に遷移する</a>
	</div>
</div>

</body>
</html>