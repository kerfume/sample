package kei.quizapp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class TopAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		TopForm fb = (TopForm) form;
		if (fb.getHid().equals("borara")) {//トークンチェック
		
		q_beans qList;//問題格納用beans宣言
		
		//問題数取得
		int qmax = Integer.parseInt(fb.getQmax());

		// セッションの初期化
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();//既存のセッションを破棄
		}
		session = request.getSession(true);
		
		session.setAttribute("point", 1);//初期pointをセッションスコープにセット
		session.setAttribute("qmax", qmax);//問題数をセッションスコープにセット
		
		
		qList = GETDB.GetAll(qmax);//問題取得
		
		System.out.println("取得問題数" + qList.getQuizList().size());
		
		session.setAttribute("qList", qList);//問題Listをセッションスコープにセット
		session.setAttribute("quiz", qList.get(0));//問1をセッションスコープにセット（以降問題位置ははpoint(-1)と同期）
		
		this.saveToken(request); //トーくんの生成
		// return mapping.getInputForward();
		return mapping.findForward("start");
		
		} else {//直接入力or二度押しはエラーページへ

			return mapping.findForward("err");
		}
	}

}
