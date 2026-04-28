<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<p>氏名：${student.name}（${student.no}）</p>

<c:choose>
    <c:when test="${not empty studentTests}">
        <table class="table table-hover">
            <thead>
                <tr>
                    <th>科目名</th>
                    <th>科目コード</th>
                    <th>回数</th>
                    <th>点数</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="test" items="${studentTests}">
                    <tr>
                        <td>${test.subjectName}</td>
                        <td>${test.subjectCd}</td>
                        <td>${test.num}</td>
                        <td>${test.point}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <p>成績情報が存在しませんでした。</p>
    </c:otherwise>
</c:choose>