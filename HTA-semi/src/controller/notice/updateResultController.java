package test.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.dao.AdminDAO;
import test.vo.NoticeVO;

/**
 * Servlet implementation class updateResultController
 */
@WebServlet("/updateResult.do")
public class updateResultController extends HttpServlet {
	//������ ���� �޾� ó���ϴ� ��Ʈ�ѷ�
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int num = Integer.parseInt(request.getParameter("noti_no"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		//Date regdate = (Date)request.getParameter("regdate");
		NoticeVO vo = new NoticeVO(num, title, content, null);
		AdminDAO dao = AdminDAO.getInstance();
		int result = dao.update(vo);
		if (result>0) {
			response.sendRedirect("notice.jsp");
		} else {
			request.setAttribute("errMsg2", "���� ������ ���� ��������");
			RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
		}
		
	}

}
