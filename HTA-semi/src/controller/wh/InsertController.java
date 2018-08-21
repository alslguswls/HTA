package controller.wh;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.wh.MembersDao;
import vo.wh.MembersVo;

@WebServlet("/insert.do")
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
		MembersVo vo=new MembersVo(id, pwd, email, phone, addr, 0, 0.0);
		dao.insert(vo);
		
		response.sendRedirect("main.jsp");
	}
}
