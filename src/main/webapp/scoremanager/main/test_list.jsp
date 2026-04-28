<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/navigation.jsp" %>

<section class="me-4">
    <h2 class="h3 mb-3 fw-normal">成績参照</h2>

    <%-- 科目情報からの検索フォーム --%>
    <div class="bg-light p-3 mb-4 border">
        <form action="TestList.action" method="get">
            <input type="hidden" name="f" value="sj">
            <div class="row g-3">
                <div class="col-md-3">
                    <label class="form-label">入学年度</label>
                    <select name="f1" class="form-select">
                        <option value="0">--------</option>
                        <c:forEach var="year" items="${ent_year_set}">
                            <option value="${year}" <c:if test="${year == param.f1}">selected</c:if>>${year}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-3">
                    <label class="form-label">クラス</label>
                    <select name="f2" class="form-select">
                        <option value="0">--------</option>
                        <c:forEach var="cNum" items="${class_num_set}">
                            <option value="${cNum}" <c:if test="${cNum == param.f2}">selected</c:if>>${cNum}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-3">
                    <label class="form-label">科目</label>
                    <select name="f3" class="form-select">
                        <option value="0">--------</option>
                        <c:forEach var="subject" items="${subject_set}">
                            <option value="${subject.cd}" <c:if test="${subject.cd == param.f3}">selected</c:if>>${subject.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-3 d-flex align-items-end">
                    <button type="submit" class="btn btn-secondary">検索</button>
                </div>
            </div>
            <c:if test="${not empty sj_error}">
                <div class="text-danger mt-2">${sj_error}</div>
            </c:if>
        </form>
    </div>

    <hr>

    <%-- 学生情報からの検索フォーム --%>
    <div class="bg-light p-3 mb-4 border">
        <form action="TestList.action" method="get">
            <input type="hidden" name="f" value="st">
            <div class="row g-3 align-items-end">
                <div class="col-md-4">
                    <label class="form-label">学生番号</label>
                    <input type="text" name="f4" class="form-control" value="${param.f4}" placeholder="学生番号を入力してください" maxlength="10" required>
                </div>
                <div class="col-md-3">
                    <button type="submit" class="btn btn-secondary">検索</button>
                </div>
            </div>
            <c:if test="${not empty st_error}">
                <div class="text-danger mt-2">${st_error}</div>
            </c:if>
        </form>
    </div>

    <%-- 検索結果の表示エリア (条件に応じてインクルード) --%>
    <c:choose>
        <c:when test="${param.f == 'sj' and empty sj_error}">
            <jsp:include page="test_list_subject.jsp" />
        </c:when>
        <c:when test="${param.f == 'st' and empty st_error}">
            <jsp:include page="test_list_student.jsp" />
        </c:when>
    </c:choose>

</section>

<%@ include file="../common/footer.jsp" %>