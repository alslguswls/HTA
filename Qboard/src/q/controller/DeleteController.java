package q.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import q.dao.BoardDao;
import q.vo.BoardVo;

@WebServlet("/delete.do")
public class DeleteController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		int pwd = Integer.parseInt(request.getParameter("pwd"));
		BoardDao dao=new BoardDao();
		BoardVo vo = dao.detail(num);
		if(pwd==vo.getPwd()) {
			dao.delete(num);
			request.getRequestDispatcher("/list.do").forward(request, response);
		}else {
			System.out.println("비밀번호 안 맞는데?");
		}
	}
}
