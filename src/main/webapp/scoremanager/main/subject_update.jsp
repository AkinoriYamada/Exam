<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp" >
	<c:param name="title">得点管理システム</c:param>
	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目変更</h2>
			
			<c:if test="${not empty error}">
				<div class="alert alert-danger mx-3">${error}</div>
			</c:if>

			<form action="SubjectUpdateExecute.action" method="post" class="mx-3" style="max-width: 400px;">
				<div class="mb-3">
					<label class="form-label">科目コード</label>
					<input type="text" name="cd" class="form-control-plaintext border-bottom" value="${cd}" readonly>
				</div>
				<div class="mb-3">
					<label class="form-label">科目名</label>
					<input type="text" name="name" class="form-control" value="${name}" maxlength="20" required>
				</div>
				<div class="my-3">
					<button type="submit" class="btn btn-secondary">変更</button>
				</div>
			</form>
			<div class="mx-3 mt-4">
				<a href="SubjectList.action">戻る</a>
			</div>
		</section>
	</c:param>
</c:import>