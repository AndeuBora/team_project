
/* 승환 : 911 페이지 */

package bookshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookshop.bean.MngrDBBean;

public class QnaFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");

		String book_kind = request.getParameter("book_kind");
		int book_id = Integer.parseInt(request.getParameter("book_id"));

		// book_id에 해당하는 book_title을 얻어냄
		MngrDBBean bookProcess = MngrDBBean.get_instance();
		String book_title = bookProcess.getBook_title(book_id);

		request.setAttribute("book_kind", book_kind);
		request.setAttribute("book_id", new Integer(book_id));
		request.setAttribute("book_title", book_title);
		request.setAttribute("qora", new Integer(1));
		request.setAttribute("type", new Integer(1));
		return "/qna/qnaForm.jsp";
	}

}
