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
/*
2018-08-24	회원 가입 기능 작성		윤우현 
 */
@WebServlet("/membeInsert.do")
public class InsertController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		String email=request.getParameter("email");
		String phone=request.getParameter("phone");
		String addr=request.getParameter("addr");
		//int lev=Integer.parseInt(request.getParameter("lev"));
		//Double coin=Double.parseDouble(request.getParameter("coin"));
		
		MembersDao dao=new MembersDao();
		MembersVo vo=new MembersVo(id, pwd, email, phone, addr, 0, 0L);
		int n = dao.insert(vo);
		if(n>0) {
			response.sendRedirect("layout.jsp?page=login.jsp");
		}else {
			request.setAttribute("errMsg", "오류로 인해 회원가입이 실패하였습니다.");
			RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
		}
		
		
	}
}
