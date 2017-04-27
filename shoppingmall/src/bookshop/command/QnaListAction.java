package bookshop.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookshop.bean.QnaDBBean;
import bookshop.bean.QnaDataBean;

public class QnaListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
	List<QnaDataBean> qnaLists;
	
	//DB연동 - 상품 QnA의 수를 얻어낸
	QnaDBBean qnaProcess= QnaDBBean.getInstance();
	int count = qnaProcess.getArticleCount();
	
	
	if(count > 0){//상품 QnA가 있으면 수행
		//지정한 수만큼의 상품 QnA를 얻어냄
		qnaLists = qnaProcess.getArticle(count);
		request.setAttribute("qnaLists", qnaLists);
	}
	
	request.setAttribute("count", new Integer(count));
	request.setAttribute("type", new Integer(0));
	return "/mngr/qnaProcess/qnaList.jsp";
	}

}
