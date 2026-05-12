<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp" >
	<c:param name="title">得点管理システム</c:param>
	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>
			
			<form action="TestRegist.action" method="get">
				<div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
					<div class="col-2">
						<label class="form-label" for="test-f1-select">入学年度</label>
						<select class="form-select" id="test-f1-select" name="f1">
							<option value="0">--------</option>
							<c:forEach var="year" items="${ent_year_set}">
								<option value="${year}" <c:if test="${year == param.f1}">selected</c:if>>${year}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-2">
						<label class="form-label" for="test-f2-select">クラス</label>
						<select class="form-select" id="test-f2-select" name="f2">
							<option value="0">--------</option>
							<c:forEach var="num" items="${class_num_set}">
								<option value="${num}" <c:if test="${num == param.f2}">selected</c:if>>${num}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-4">
						<label class="form-label" for="test-f3-select">科目</label>
						<select class="form-select" id="test-f3-select" name="f3">
							<option value="0">--------</option>
							<c:forEach var="sub" items="${subject_set}">
								<option value="${sub.cd}" <c:if test="${sub.cd == param.f3}">selected</c:if>>${sub.name}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-2">
						<label class="form-label" for="test-f4-select">回数</label>
						<select class="form-select" id="test-f4-select" name="f4">
							<option value="0">--------</option>
							<option value="1" <c:if test="${param.f4 == '1'}">selected</c:if>>1</option>
							<option value="2" <c:if test="${param.f4 == '2'}">selected</c:if>>2</option>
						</select>
					</div>
					<div class="col-2 text-center pt-4">
						<button type="submit" class="btn btn-secondary" id="filter-button">検索</button>
					</div>
					
					<%-- エラーメッセージ表示エリア --%>
					<c:if test="${not empty error}">
						<div class="mt-2 text-danger px-2">${error}</div>
					</c:if>
				</div>
			</form>

			<c:choose>
				<c:when test="${not empty tests}">
					<div class="px-4 mb-2 fw-bold">科目：${subject.name} (${param.f4}回目)</div>
					
					<form action="TestRegistExecute.action" method="post">
						<%-- 設計書指定のname属性 (subject_cd, count) --%>
						<input type="hidden" name="subject_cd" value="${param.f3}">
						<input type="hidden" name="count" value="${param.f4}">
						
						<table class="table table-hover mx-3">
							<tr class="table-light">
								<th>入学年度</th>
								<th>クラス</th>
								<th>学生番号</th>
								<th>氏名</th>
								<th class="text-center">点数</th>
							</tr>
							<c:forEach var="test" items="${tests}">
								<tr>
									<td class="align-middle">${test.student.entYear}</td>
									<td class="align-middle">${test.classNum}</td>
									<td class="align-middle">
										${test.student.no}
										<%-- 設計書指定のname属性 (regist) --%>
										<input type="hidden" name="regist" value="${test.student.no}">
									</td>
									<td class="align-middle">${test.student.name}</td>
									<td class="text-center">
										<%-- 設計書指定のname属性 (point_学生番号) --%>
										<input type="text" name="point_${test.student.no}" 
											   value="${test.point == -1 ? '' : test.point}" 
											   class="form-control mx-auto text-center" 
											   style="max-width: 100px;">
									</td>
								</tr>
							</c:forEach>
						</table>
						
						<div class="my-3 text-end px-4">
							<button type="submit" class="btn btn-primary">登録して終了</button>
						</div>
					</form>
				</c:when>
				<c:otherwise>
					<c:if test="${not empty param.f1 and param.f1 != '0'}">
						<div class="px-4 text-muted">学生情報が存在しませんでした。</div>
					</c:if>
				</c:otherwise>
			</c:choose>
		</section>
	</c:param>
</c:import>