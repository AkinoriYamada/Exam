<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp" >
	<c:param name="title">得点管理システム</c:param>
	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">クラス変更</h2>
			
			<c:if test="${not empty error}">
				<div class="alert alert-danger mx-3">${error}</div>
			</c:if>

			<form action="ClassUpdateExecute.action" method="post" class="mt-4 mx-3">
				<%-- 元のクラス番号を保持 --%>
				<input type="hidden" name="old_class_num" value="${class_num}">

				<div class="mb-3 w-50">
					<label class="form-label">現在のクラス番号</label>
					<div class="form-control bg-light">${class_num}</div>
				</div>

				<div class="mb-3 w-50">
					<label for="new_class_num" class="form-label">新しいクラス番号</label>
					<input type="text" class="form-control" id="new_class_num" name="new_class_num" 
						   value="${class_num}" maxlength="5" required>
					<div class="form-text text-primary">
						※クラス番号を変更すると、所属する学生と成績データのクラス情報もすべて自動更新されます。
					</div>
				</div>

				<div class="mt-4">
					<button type="submit" class="btn btn-primary">変更</button>
					<a href="ClassList.action" class="btn btn-secondary ms-2">戻る</a>
				</div>
			</form>
		</section>
	</c:param>
</c:import>