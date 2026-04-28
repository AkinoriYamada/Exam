<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/navigation.jsp" %>

<section class="me-4">
    <h2 class="h3 mb-3 fw-normel">成績参照</h2>
    
    <div class="bg-light p-3 mb-4">
        <form action="TestList.action" method="get">
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
        </form>
    </div>

    <c:choose>
        <c:when test="${not empty tests}">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>入学年度</th>
                        <th>クラス</th>
                        <th>学生番号</th>
                        <th>氏名</th>
                        <th>回数</th>
                        <th>点数</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="test" items="${tests}">
                        <tr>
                            <td>${param.f1}</td>
                            <td>${test.classNum}</td>
                            <td>${test.student.no}</td>
                            <td>${test.student.name}</td>
                            <td>${test.no}</td>
                            <td>${test.point}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <c:if test="${not empty param.f1}">
                <p>成績情報が存在しませんでした</p>
            </c:if>
        </c:otherwise>
    </c:choose>
</section>

<%@ include file="../common/footer.jsp" %>