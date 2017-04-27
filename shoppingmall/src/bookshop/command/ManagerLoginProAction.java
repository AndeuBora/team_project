package bookshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookshop.bean.MngrDBBean;

public class ManagerLoginProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 보라 804page~
		request.setCharacterEncoding("utf-8"); //한글 인코딩
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		
		MngrDBBean dao = MngrDBBean.get_instance();
		int check = dao.user_check(id, passwd); // 관리자 인증메소드 결과숫자로받음
		
		request.setAttribute("check", new Integer(check));
		request.setAttribute("id", id);
		return "/mngr/logon/mLoginPro.jsp";
	}

}
