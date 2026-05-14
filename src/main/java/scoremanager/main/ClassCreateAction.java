package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class ClassCreateAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // クラス登録画面（class_create.jsp）を表示するだけの処理
        req.getRequestDispatcher("class_create.jsp").forward(req, res);
    }
}