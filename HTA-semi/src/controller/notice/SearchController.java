package test.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.regexp.internal.recompile;

import test.dao.AdminDAO;
import test.vo.NoticeVO;

/**
 * Servlet implementation class SearchController
 */
@WebServlet("/search.do")
public class SearchController extends HttpServlet {
	//검색된 공지사항 호출 위한 컨트롤러
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String search = request.getParameter("search");
		String keyword = request.getParameter("keyword");
		request.getSession().setAttribute("search", search);
		request.getSession().setAttribute("keyword", keyword);
		NoticeVO vo = new NoticeVO();
		AdminDAO dao = AdminDAO.getInstance();
		String spageNum=request.getParameter("pageNum2");////
		int pageNum=1;
		if (spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow = (pageNum-1)*10+1;
		int endRow=startRow+9;
		
		ArrayList<NoticeVO> list2 = dao.search(search, keyword, startRow, endRow);
		int pageCount = (int)Math.ceil(dao.getCount2(search,keyword)/10.0);
		int startPage = ((pageNum-1)/10*10)+1;
		int endPage= startPage + 9;
		if (endPage>pageCount) {
			endPage=pageCount;
		}
		
		
		request.setAttribute("list2", list2);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		RequestDispatcher rd = request.getRequestDispatcher("/searchList.jsp");
		rd.forward(request, response);
	}

}
