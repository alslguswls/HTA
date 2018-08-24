package test.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.dao.AdminDAO;
import test.vo.NoticeVO;

/**
 * Servlet implementation class updateController
 */
@WebServlet("/update.do")
public class updateController extends HttpServlet {
	//수정을 위해 자료 호출하는 컨트롤러
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int num = Integer.parseInt(request.getParameter("noti_no"));
		NoticeVO vo = new NoticeVO();
		AdminDAO dao = AdminDAO.getInstance();
		vo = dao.invite(num);
		request.setAttribute("vo",vo);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/update.jsp");
		rd.forward(request, response);
		
	}

}
