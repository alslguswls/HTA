package controller.notice;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.notice.AdminDAO;



@WebServlet("/notidelete.do")
public class DeleteController extends HttpServlet {
	//공지사항 삭제를 위한 컨트롤러
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int num = Integer.parseInt(request.getParameter("noti_no"));
		System.out.println("참다랑어");
		AdminDAO dao = AdminDAO.getInstance();
		int ans = dao.delete(num);
		if (ans>0) {
			response.sendRedirect("noticeList.do");
		} else {
			request.setAttribute("errMsg3", "삭제의 오류로 인한 에러");
			RequestDispatcher rd = request.getRequestDispatcher("/layout.jsp?page=notiError.jsp&left=noticeLeft.jsp");
			rd.forward(request, response);
		}
	}

}
