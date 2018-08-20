package q.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import q.dao.BoardDao;
import q.vo.BoardVo;

@WebServlet("/list.do")
public class ListController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//String search = request.getParameter("search");
		//String keyword = request.getParameter("keyword");
		String search = null;
		String keyword = null;
		if(request.getParameter("search") !=null){
			search = request.getParameter("search");
			keyword = request.getParameter("keyword");
			request.getSession().setAttribute("search", search);
			request.getSession().setAttribute("keyword", keyword);
		}else if(request.getSession().getAttribute("search") != null){
			search = (String)request.getSession().getAttribute("search");
			keyword = (String)request.getSession().getAttribute("keyword");
		}
		System.out.println(search + keyword);
		String spageNum = request.getParameter("pageNum");
		int pageNum = 1;
		if(spageNum!=null) {
			pageNum = Integer.parseInt(spageNum);
		}
		//int startRow=(pageNum-1)*10+1;
		//int endRow=startRow+9;
		int startRow = pageNum*5-4;
		int endRow = startRow+4;
		BoardDao dao = new BoardDao();
		ArrayList<BoardVo> list = dao.list(startRow, endRow, search, keyword);
		//전체페이지갯수구하기
		int pageCount;
		if(search!=null) {
			pageCount = (int)Math.ceil(dao.getSearchCount(search, keyword)/5.0);
		}else {
			pageCount = (int)Math.ceil(dao.getCount()/5.0);
		}
		//시작페이지번호
		int startPage = ((pageNum-1)/4*4)+1;
		//int startPage = ((pageNum/4)+1)*4-3;
		//끝페이지번호
		//int endPage = startPage+pageNum%10-1;
		int endPage = startPage+3;
		if(endPage>pageCount) {
			endPage=pageCount;
		}
		System.out.print(startPage + " " + endPage + " " + pageCount + " " + pageNum);
		request.setAttribute("list",list);
		request.setAttribute("pageCount",pageCount);
		request.setAttribute("startPage",startPage);
		request.setAttribute("endPage",endPage);
		request.setAttribute("pageNum",pageNum);
		request.getRequestDispatcher("/list.jsp").forward(request, response);
	}
}