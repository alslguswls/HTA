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




@WebServlet("/notiupdateResult.do")
public class UpdateResultController extends HttpServlet {
	//수정 결과를 처리하는 컨트롤러
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		int num = Integer.parseInt(request.getParameter("noti_no"));
		System.out.println(num);
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		//Date regdate = (Date)request.getParameter("regdate");
		NoticeVO vo4 = new NoticeVO(num, title, content, null);
		AdminDAO dao = AdminDAO.getInstance();
		int result = dao.update(vo4);
		if (result>0) {
			response.sendRedirect("noticeList.do");
		} else {
			request.setAttribute("errMsg2", "수정의 오류로 인한 에러");
			RequestDispatcher rd = request.getRequestDispatcher("/layout.jsp?page=notiError.jsp");
			rd.forward(request, response);
		}
		
	}

}
