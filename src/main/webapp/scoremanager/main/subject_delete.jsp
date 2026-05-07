<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp" >
	<c:param name="title">得点管理システム</c:param>
	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目削除</h2>
			
			<form action="SubjectDeleteExecute.action" method="post" class="mx-3">
				<input type="hidden" name="subject_cd" value="${subject_cd}">
				<input type="hidden" name="subject_name" value="${subject_name}">
				
				<%-- 設計書指定の削除確認メッセージ「〇〇（コード）を削除しますか？」 --%>
				<p>科目「${subject_name}（${subject_cd}）」を削除しますか？</p>
				
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