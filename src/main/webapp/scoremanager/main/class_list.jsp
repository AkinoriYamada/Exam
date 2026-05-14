<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp" >
	<c:param name="title">得点管理システム</c:param>
	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">クラス管理</h2>
			
			<div class="my-3 text-end">
				<a href="ClassCreate.action" class="btn btn-success">新規登録</a>
			</div>
			
			<%-- エラーメッセージ表示（削除不可時など） --%>
			<c:if test="${not empty error}">
				<div class="alert alert-danger mx-3">${error}</div>
			</c:if>

			<table class="table table-hover mt-3">
				<thead>
					<tr class="table-light">
						<th>クラス番号</th>
						<th class="text-center">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="num" items="${class_nums}">
						<tr>
							<td class="align-middle">${num}</td>
							<td class="text-center">
								<%-- 変更ボタン --%>
								<a href="ClassUpdate.action?class_num=${num}" class="btn btn-primary btn-sm">変更</a>
								
								<%-- 削除ボタン（確認ダイアログ付き） --%>
								<form action="ClassDeleteExecute.action" method="post" style="display:inline;" 
									  onsubmit="return confirm('クラス ${num} を削除しますか？\n※所属学生が0名の場合のみ削除可能です。');">
									<input type="hidden" name="class_num" value="${num}">
									<button type="submit" class="btn btn-danger btn-sm ms-2">削除</button>
								</form>
							</td>
						</tr>
					</c:forEach>
					<c:if test="${empty class_nums}">
						<tr>
							<td colspan="2" class="text-center">登録されているクラスがありません。</td>
						</tr>
					</c:if>
				</tbody>
			</table>
		</section>
	</c:param>
</c:import>