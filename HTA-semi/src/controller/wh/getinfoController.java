package controller.wh;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.wh.MembersDao;
import vo.wh.MembersVo;

/**
2018-08-27	회원 수정 시 조회 기능 작성중		윤우현 
 */
@WebServlet("/memberGetinfo.do")
public class getinfoController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		MembersDao dao = new MembersDao();
		MembersVo vo = dao.getinfo(id);
		
		if(vo != null) {
			request.setAttribute("id", vo.getId());
			request.setAttribute("pwd", vo.getPwd());
			request.setAttribute("email", vo.getEmail());
			request.setAttribute("phone", vo.getPhone());
			request.setAttribute("addr", vo.getAddr());
			request.setAttribute("coin", vo.getCoin());
			
			RequestDispatcher rd = request.getRequestDispatcher("layout.jsp?page=/update.jsp&left=admin.jsp");
			rd.forward(request, response);
		}else {
			request.setAttribute("errMsg", "회원정보 조회 실패");
			RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
		}
	}

}
