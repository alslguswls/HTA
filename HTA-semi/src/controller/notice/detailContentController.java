package controller.notice;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.notice.AdminDAO;
import vo.notice.NoticeVO;

/**
 * Servlet implementation class detailContentController
 */
@WebServlet("/detailContent.do")
public class detailContentController extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		int num = Integer.parseInt(request.getParameter("noti_no"));
		
		NoticeVO vo2 = new NoticeVO();
		AdminDAO dao = AdminDAO.getInstance();
		vo2 = dao.invite(num);
		request.setAttribute("vodetail",vo2);
		
		RequestDispatcher rd = request.getRequestDispatcher("/layout.jsp?page=noticeDetail.jsp");
		rd.forward(request, response);
	}

}
