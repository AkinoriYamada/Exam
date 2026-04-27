<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="UTF-8">
	<title>科目管理一覧</title>
	<style>
		body { font-family: sans-serif; margin: 20px;}
		h2 { border-bottom: 2px solid #333; padding-bottom: 5px; }
		table { width: 100%; border-collapse: collapse; margin-top: 15px; }
		th, td { border: 1px solid #ccc; padding: 10px; text-align: left; }
		th { background-color: #f2f2f2; }
		.link-group {margin-bottom: 15px; }
		.btn { padding: 5px 10px; text-decoration: none; border-radius: 3px; }
		.btn-register { background-color: #28a745; color: white;}
		.btn-edit { background-color: #007bff; color: white;}
		.btn-delete { background-color: #dc3545; color: white;}
	</style>
</head>
<body>
	<h2>科目管理一覧</h2>
	
	<div class="link-group">
		<a href="SubjectRegisterServlet" class="btn btn-register">新規科目登録</a>
	</div>
	<table>
		<thead>
			<tr>
				<th>科目コード</th>
				<th>科目名</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<%-- ログインユーザの学校コードに紐づく科目情報を表示--%>
			<c:forEach var="subject" items="${subjectList}">
				<tr>
					<td>${subject.subjectCode}</td>
					
					<td><c:out value="${subject.subjectName}" /></td>
					
					<td><a href="SubjectEditServlet?id=${subject.id}" class="btn btn-edit">変更</a>
						
						<a href="SubjectDeleteServlet?id=${subject.id}" class="btn btn-delete" oneclick="return confirm('この科目を削除してもよろしいですか？');">削除</a>
					</td>
				</tr>
			</c:forEach>
			
			<%-- データがない場合の処理 --%>
			<c:if test="${empty subjectList}">
				<tr>
					<td colspan="3" style="text-align:center;">表示する科目情報がありません。</td>
				</tr>
			</c:if>
		</tbody>
	</table>
</body>
</html>