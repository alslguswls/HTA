package test.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.dao.AdminDAO;
import test.vo.NoticeVO;

/**
 * Servlet implementation class NoticeListController
 */
@WebServlet("/noticeList.do")
public class NoticeListController extends HttpServlet {
	//공지 리스트 전체 호출위한 컨트롤러
  
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		NoticeVO vo = new NoticeVO();
		AdminDAO dao = AdminDAO.getInstance();
		String spageNum=request.getParameter("pageNum");
		int pageNum=1;
		if (spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		
		int startRow = (pageNum-1)*10+1;
		int endRow=startRow+9;
		ArrayList<NoticeVO> list = dao.all(startRow,endRow);
		int pageCount = (int)Math.ceil(dao.getCount()/10.0);
		int startPage = ((pageNum-1)/10*10)+1;
		int endPage= startPage + 9;
		if (endPage>pageCount) {
			endPage=pageCount;
		}
		request.setAttribute("list", list);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.getRequestDispatcher("/notice.jsp").forward(request, response);
		
		
		
		
	}

}
