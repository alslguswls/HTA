package controller.wh;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.wh.MembersDao;

/**
2018-08-27	회원 삭제 기능 작성		윤우현
 */
@WebServlet("/memberDelete.do")
public class deleteController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		MembersDao dao = new MembersDao();
		int n = dao.del(id);
		
		if(n>0) {
			// lev '9'로 업데이트 되었을시
			response.sendRedirect("memberList.do");
		}else {
			request.setAttribute("errMsg", "오류로 인해 회원삭제를 실패하였습니다.");
			RequestDispatcher rd=request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
		}
		
	}
}
