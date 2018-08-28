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



@WebServlet("/notiupdate.do")
public class UpdateController extends HttpServlet {
	//수정을 위해 특정 공지사항을 호출하는 컨트롤러
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int num = Integer.parseInt(request.getParameter("noti_no"));
		
		NoticeVO vo2 = new NoticeVO();
		AdminDAO dao = AdminDAO.getInstance();
		vo2 = dao.invite(num);
		request.setAttribute("vo2",vo2);
		
		RequestDispatcher rd = request.getRequestDispatcher("/layout.jsp?page=noticeUpdate.jsp");
		rd.forward(request, response);
		
	}

}