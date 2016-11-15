package kei.quizapp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class QuizScrAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		if (this.isTokenValid(request, true)) {//トークンチェック

			QuizScrForm fb = (QuizScrForm) form;
			
			int ans = Integer.parseInt(fb.getAns());
			int point = (int) session.getAttribute("point");
			q_beans qList = (q_beans) session.getAttribute("qList");

			if (ans == qList.get(point - 1).getAnswer()) {
				qList.get(point - 1).setErrata(1);
			} else {
				qList.get(point - 1).setErrata(0);
			}
			fb.setAns("");// ラジオボタンの選択外すマン
			this.saveToken(request); 
			return mapping.getInputForward();

		} else {//直接入力or二度押しはエラーページへ
			if (session != null) {
				session.invalidate();// 既存のセッションを破棄
			}
			

			return mapping.findForward("err");
		}
		// return mapping.findForward("answer");
	}
}
