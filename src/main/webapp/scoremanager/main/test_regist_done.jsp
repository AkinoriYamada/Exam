<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp" >
    <c:param name="title">得点管理システム</c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績登録完了</h2>
            
            <div class="mt-4">
                <p>登録が正常に完了しました。</p>
            </div>

            <div class="mt-4">
                <%-- 成績管理一覧（登録画面）に戻る --%>
                <a href="TestRegist.action" class="btn btn-primary">戻る</a>
                <%-- 成績参照画面へ遷移 --%>
                <a href="TestList.action" class="btn btn-secondary ms-2">成績参照へ</a>
            </div>
        </section>
    </c:param>
</c:import>