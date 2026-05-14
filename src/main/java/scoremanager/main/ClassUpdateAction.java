package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class ClassUpdateAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // URLパラメータから変更対象のクラス番号を取得
        String classNum = req.getParameter("class_num");

        // 変更画面（class_update.jsp）の初期値としてセット
        req.setAttribute("class_num", classNum);

        // クラス変更画面へフォワード
        req.getRequestDispatcher("class_update.jsp").forward(req, res);
    }
}