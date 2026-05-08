<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="UTF=8">
	<title>科目登録</title>
	<style>
		body { font-family: sans-serif; margin: 40px; line-height: 1.6; }
		.container { max-width: 600px; margin: auto; border:1px solid #ccc; padding: 20px; border-radius: 8px;}
		h2 { border-bottom: 2px solid #333; padding-bottom: 10px;}
		.form-group { margin-bottom: 15px; }
		label { display: black; font-weight: bold; margin-bottom: 5px; }
		input[type="text"] { width: 100%; padding: 8px; box-sizing: border-box; border: 1px solid #999; border-radius: 4px; }
		input[type="text"]:focus { border-color: #007bff; outline: none;}
		.btn-group { margin-top: 20px; display: flex; align-items: center; gap: 20px;}
		.submit-btn { background-color: 28a745; color: white; padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer; font-size: 16px;}
		.submit-btn:hover { background-color: #218838;}
		.back-link {text-decoration: none; color: #007bff; font-size: 14px; }
		.back-link:hover { text-decoration: underline; }
	</style>
</head>
<body>
<div class="container">
	<h2>科目登録</h2>
	
	<form action="SubjectCreateServlet" method="post">
		<div class="form-group">
			<label for="cd">科目コード</label>
			<input type="text" id="cd" name="cd" value="<c:out value='${cd}' />" maxlength="3" placeholder="科目コードを入力してください" required>
		</div>
		<div class="form-group">
			<label for="name">科目名</label>
			<input type="text" id="name" name="name" value="<c:out value='${name}'/>" maxlength="20" placeholder="科目名を入力してください" required>
		</div>
		
		<div class="btn-group">
			<button type="submit" class="submit-btn">登録</button>
			<a href="SubjectListServlet" class="back-link">科目管理一覧画面に戻る</a>
		</div>
	</form>
</div>
</body>
</html>