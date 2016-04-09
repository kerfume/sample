package kei.quizapp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ResultAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (this.isTokenValid(request, true)) {
			// AnsScrForm fb = (AnsScrForm)form;
			HttpSession session = request.getSession(false);
			if (session != null) {
				session.invalidate();// 既存のセッションを破棄
			}
			response.sendRedirect("/QuizForStruts/Top.jsp");
			return null;
			//return mapping.findForward("top");
		} else {
			return mapping.findForward("err");
		}
	}
}
