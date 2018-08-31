package controller.notice;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import category.dao.CategoryDao;
import category.vo.CategoryVo;
import dao.notice.AdminDAO;
import vo.notice.NoticeVO;



@WebServlet("/noticeList.do")
public class NoticeListController extends HttpServlet {
	//공지사항 항목 호출을 위한 컨트롤러
  
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//String page = request.getParameter("page");
		//System.out.println(page);
		request.setCharacterEncoding("utf-8");
		NoticeVO vo = new NoticeVO();
		AdminDAO dao = AdminDAO.getInstance();
		String spageNum=request.getParameter("pageNum");//공지사항 현재 페이지를 알려주는 파라미터
		System.out.println(spageNum);
		int pageNum=1;
		if (spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		
		int startRow = (pageNum-1)*10+1;
		int endRow=startRow+9;
		//System.out.println(page);
		ArrayList<NoticeVO> list = dao.all(startRow,endRow);//공지사항 전체 호출
		int pageCount = (int)Math.ceil(dao.getCount()/10.0);//공지사항 전체 개수를 호출하는 dao
		int startPage = ((pageNum-1)/10*10)+1;
		int endPage= startPage + 9;
		if (endPage>pageCount) {
			endPage=pageCount;
		}
		
	//	CategoryDao cdao = CategoryDao.getInstance();
	//	ArrayList<CategoryVo> list1=new ArrayList<CategoryVo>();
	//	list1=cdao.leftList();
		
		
		//System.out.println(page);
		request.setAttribute("list22", list);
	//	request.setAttribute("list1", list1);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.getRequestDispatcher("/layout.jsp?page=notice.jsp&left=noticeLeft.jsp").forward(request, response);
		
		
		
		
	}

}