package kei.quizapp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AnsScr2Action extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		if (this.isTokenValid(request, true)) {
			// AnsScrForm fb = (AnsScrForm)form;

			int point = (int) session.getAttribute("point");
			q_beans qList = (q_beans) session.getAttribute("qList");

			point++;
			session.setAttribute("point", point);// pointを更新
			session.setAttribute("correctnum", qList.getCorrect());

			// session.setAttribute("quiz",qList.get(point-1));//対象問題を更新
			// return mapping.getInputForward();
			this.saveToken(request);
			return mapping.findForward("result");
		} else {
			if (session != null) {
				session.invalidate();// 既存のセッションを破棄
			}

			return mapping.findForward("err");

		}
	}
}