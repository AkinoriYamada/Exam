<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp" >
	<c:param name="title">得点管理システム</c:param>
	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目管理　削除</h2>
			
			<form action="SubjectDeleteExecute.action" method="post" class="mx-3">
				<%-- 削除対象の特定用データを隠し項目として送信 --%>
				<input type="hidden" name="subject_cd" value="${subject_cd}">
				<input type="hidden" name="subject_name" value="${subject_name}">
				
				<p>科目コード「${subject_cd}」科目名「${subject_name}」を削除しますか？</p>
				
				<div class="my-4">
					<button type="submit" class="btn btn-danger">削除</button>
				</div>
			</form>
			<div class="mx-3 mt-4">
				<a href="SubjectList.action">戻る</a>
			</div>
		</section>
	</c:param>
</c:import>