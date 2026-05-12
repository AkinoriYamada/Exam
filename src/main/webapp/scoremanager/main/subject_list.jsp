<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp" >
	<c:param name="title">得点管理システム</c:param>
	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目管理</h2>
			
			<div class="my-2 text-end px-4">
				<a href="SubjectCreate.action">新規登録</a>
			</div>
			
			<c:if test="${not empty error}">
				<div class="alert alert-danger mx-3">${error}</div>
			</c:if>

			<c:choose>
				<c:when test="${not empty subjects and subjects.size() > 0}">
					<table class="table table-hover mx-3">
						<tr class="table-light">
							<th>科目コード</th>
							<th>科目名</th>
							<th></th>
							<th></th>
						</tr>
						<c:forEach var="subject" items="${subjects}">
							<tr>
								<td class="align-middle">${subject.cd}</td>
								<td class="align-middle">${subject.name}</td>
								<td><a href="SubjectUpdate.action?cd=${subject.cd}">変更</a></td>
								<td><a href="SubjectDelete.action?cd=${subject.cd}">削除</a></td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
				<c:otherwise>
					<div class="px-4 text-muted">科目情報が存在しませんでした。</div>
				</c:otherwise>
			</c:choose>
		</section>
	</c:param>
</c:import>