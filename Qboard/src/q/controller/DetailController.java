package q.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import q.dao.BoardDao;
import q.vo.BoardVo;


@WebServlet("/detail.do")
public class DetailController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num=Integer.parseInt(request.getParameter("num"));
		BoardDao dao=new BoardDao();
		BoardVo vo=dao.detail(num);
		request.setAttribute("vo",vo);
		request.getRequestDispatcher("/detail.jsp").forward(request, response);
	}
}