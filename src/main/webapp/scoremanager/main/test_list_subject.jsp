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
								<option value="${year}" <c:if test="${year == param.f1}">selected</c:if>>${year}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-3">
						<label class="form-label">クラス</label>
						<select class="form-select" name="f2">
							<option value="0">--------</option>
							<c:forEach var="num" items="${class_num_set}">
								<option value="${num}" <c:if test="${num == param.f2}">selected</c:if>>${num}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-4">
						<label class="form-label">科目</label>
						<select class="form-select" name="f3">
							<option value="0">--------</option>
							<c:forEach var="sub" items="${subject_set}">
								<option value="${sub.cd}" <c:if test="${sub.cd == param.f3}">selected</c:if>>${sub.name}</option>
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
						<input type="text" name="f4" class="form-control" placeholder="学生番号を入力してください">
					</div>
					<div class="col-2 text-center pt-4">
						<button class="btn btn-secondary w-100">検索</button>
					</div>
				</div>
			</form>

			<c:if test="${not empty tests}">
				<div class="px-4 mb-2 fw-bold">科目：${subject.name}</div>
				<table class="table table-hover mx-3 mt-3">
					<tr class="table-light">
						<th>入学年度</th>
						<th>クラス</th>
						<th>学生番号</th>
						<th>氏名</th>
						<th class="text-center">1回目</th>
						<th class="text-center">2回目</th>
					</tr>
					<c:forEach var="item" items="${tests}">
						<tr>
							<td>${item.entYear}</td>
							<td>${item.classNum}</td>
							<td>${item.studentNo}</td>
							<td>${item.studentName}</td>
							<td class="text-center">${item.getPoint(1)}</td>
							<td class="text-center">${item.getPoint(2)}</td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</section>
	</c:param>
</c:import>