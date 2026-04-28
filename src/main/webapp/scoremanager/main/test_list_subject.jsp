<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<p>科目：${subject_name}</p>

<c:choose>
    <c:when test="${not empty subjects}">
        <table class="table table-hover">
            <thead>
                <tr>
                    <th>入学年度</th>
                    <th>クラス</th>
                    <th>学生番号</th>
                    <th>氏名</th>
                    <th>１回の点数</th>
                    <th>２回の点数</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="sub" items="${subjects}">
                    <tr>
                        <td>${sub.entYear}</td>
                        <td>${sub.classNum}</td>
                        <td>${sub.studentNo}</td>
                        <td>${sub.studentName}</td>
                        <td>${sub.getPoint(1)}</td>
                        <td>${sub.getPoint(2)}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <p>学生情報が存在しませんでした。</p>
    </c:otherwise>
</c:choose>