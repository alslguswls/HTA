package test.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.dao.AdminDAO;

/**
 * Servlet implementation class DeleteController
 */
@WebServlet("/delete.do")
public class DeleteController extends HttpServlet {
	//공지사항 삭제를 위한 컨트롤러
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int num = Integer.parseInt(request.getParameter("noti_no"));
		AdminDAO dao = AdminDAO.getInstance();
		int ans = dao.delete(num);
		if (ans>0) {
			response.sendRedirect("/notice.jsp");
		} else {
			request.setAttribute("errMsg3", "삭제 오류로 인한 삭제 실패");
			RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
		}
	}

}
