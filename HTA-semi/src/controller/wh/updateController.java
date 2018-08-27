package controller.wh;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.wh.MembersDao;
import javafx.scene.control.Alert;
import vo.wh.MembersVo;

/**
2018-08-27	회원 수정 기능 작성중		윤우현 
 */
@WebServlet("/memberUpdate.do")
public class updateController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String addr = request.getParameter("addr");
		Long coin = Long.parseLong(request.getParameter("coin"));
		
		MembersDao dao = new MembersDao();
		MembersVo vo = new MembersVo(id, pwd, email, phone, addr, 0, coin);
		int n = dao.update(vo);
		
		if(n>0) {
			response.sendRedirect("layout.jsp?page=memberList.do");
		}else {
			request.setAttribute("errMsg", "오류로 인해 회원 수정이 실패 하였습니다");
			RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
		}
	}
}
