package q.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import q.dao.BoardDao;
import q.vo.BoardVo;
@WebServlet("/insert.do")
public class InsertController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String writer=request.getParameter("writer");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		int pwd=Integer.parseInt(request.getParameter("pwd"));
		BoardVo vo=new BoardVo(0, writer, title, content, 0, pwd);
		BoardDao dao=new BoardDao();
		int n = dao.insert(vo);
		if(n>0) {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else {
			System.out.println("<h1>글쓰기 실패<h1>");
		}
	}
}