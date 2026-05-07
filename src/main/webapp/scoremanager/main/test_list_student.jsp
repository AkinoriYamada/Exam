<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp" >
	<c:param name="title">得点管理システム</c:param>
	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績参照</h2>
			
			<c:if test="${not empty error}">
				<div class="alert alert-danger mx-3">${error}</div>
			</c:if>

			<form action="TestList.action" method="get">
				<input type="hidden" name="f" value="sj">
				<div class="row border mx-3 mb-3 py-2 align-items-center rounded bg-light">
					<div class="col-12 mb-2 fw-bold">科目情報から検索</div>
					<div class="col-3">
						<label class="form-label">入学年度</label>
						<select class="form-select" name="f1">
							<option value="0">--------</option>
							<c:forEach var="year" items="${ent_year_set}">
								<option value="${year}">${year}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-3">
						<label class="form-label">クラス</label>
						<select class="form-select" name="f2">
							<option value="0">--------</option>
							<c:forEach var="num" items="${class_num_set}">
								<option value="${num}">${num}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-4">
						<label class="form-label">科目</label>
						<select class="form-select" name="f3">
							<option value="0">--------</option>
							<c:forEach var="sub" items="${subject_set}">
								<option value="${sub.cd}">${sub.name}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-2 text-center pt-4">
						<button class="btn btn-secondary w-100">検索</button>
					</div>
				</div>
			</form>

			<form action="TestList.action" method="get">
				<input type="hidden" name="f" value="st">
				<div class="row border mx-3 mb-4 py-2 align-items-center rounded bg-light">
					<div class="col-12 mb-2 fw-bold">学生情報から検索</div>
					<div class="col-10">
						<label class="form-label">学生番号</label>
						<input type="text" name="f4" class="form-control" value="${param.f4}" placeholder="学生番号を入力してください">
					</div>
					<div class="col-2 text-center pt-4">
						<button class="btn btn-secondary w-100">検索</button>
					</div>
				</div>
			</form>

			<c:if test="${not empty student}">
				<div class="px-4 mb-2 fw-bold">氏名：${student.name} (${student.no})</div>
				
				<c:if test="${not empty studentTests}">
					<table class="table table-hover mx-3 mt-3">
						<tr class="table-light">
							<th>科目名</th>
							<th>科目コード</th>
							<th>回数</th>
							<th class="text-center">点数</th>
						</tr>
						<c:forEach var="test" items="${studentTests}">
							<tr>
								<td>${test.subjectName}</td>
								<td>${test.subjectCd}</td>
								<td>${test.num}</td>
								<td class="text-center">${test.point}</td>
							</tr>
						</c:forEach>
					</table>
				</c:if>
			</c:if>
		</section>
	</c:param>
</c:import>