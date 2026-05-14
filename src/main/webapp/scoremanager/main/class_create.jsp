<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp" >
	<c:param name="title">得点管理システム</c:param>
	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">クラス登録</h2>
			
			<c:if test="${not empty error}">
				<div class="alert alert-danger mx-3">${error}</div>
			</c:if>

			<form action="ClassCreateExecute.action" method="post" class="mt-4 mx-3">
				<div class="mb-3 w-50">
					<label for="class_num" class="form-label">クラス番号</label>
					<input type="text" class="form-control" id="class_num" name="class_num" 
						   maxlength="5" required placeholder="例：131">
					<div class="form-text">5文字以内の半角英数字で入力してください。</div>
				</div>
				<div class="mt-4">
					<button type="submit" class="btn btn-primary">登録</button>
					<a href="ClassList.action" class="btn btn-secondary ms-2">戻る</a>
				</div>
			</form>
		</section>
	</c:param>
</c:import>