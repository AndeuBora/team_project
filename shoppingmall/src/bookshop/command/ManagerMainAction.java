package bookshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManagerMainAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//보라 799page~
		//관리자 구분할때 사용
		request.setAttribute("type", new Integer(0));
		return "/mngr/managerMain.jsp"; 
	}

}
