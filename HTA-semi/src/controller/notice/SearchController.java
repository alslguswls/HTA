package controller.notice;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import category.dao.CategoryDao;
import category.vo.CategoryVo;
import dao.notice.AdminDAO;
import vo.notice.NoticeVO;
import vo.notice.SearchVO;



@WebServlet("/notisearch.do")
public class SearchController extends HttpServlet {
	//검색된 공지사항의 목록들을 호출하는 컨트롤러
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String search = request.getParameter("search");//선택된 항목
		String keyword = request.getParameter("keyword");//쓰여진 검색어
		request.getSession().setAttribute("search", search);
		request.getSession().setAttribute("keyword", keyword);
		//System.out.println(search);
		//System.out.println(keyword);
		NoticeVO vo = new NoticeVO();
		AdminDAO dao = AdminDAO.getInstance();
		String spageNum=request.getParameter("pageNum2");////검색된 공지사항들의 현재 페이지 넘버를 호출하는 파라미터
		int pageNum=1;
		if (spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow = (pageNum-1)*10+1;
		int endRow=startRow+9;
		//System.out.println(startRow);
		//System.out.println(endRow);
		ArrayList<NoticeVO> list2 = dao.search(search, keyword, startRow, endRow);//검색된 공지사항 전체 호출
		SearchVO vose = new SearchVO(search, keyword); // searchList 페이지클릭시 예전 검색조건 전달위한vo
		int pageCount = (int)Math.ceil(dao.getCount2(search,keyword)/10.0);//검색된 공지사항의 개수 호출
		int startPage = ((pageNum-1)/10*10)+1;
		int endPage= startPage + 9;
		if (endPage>pageCount) {
			endPage=pageCount;
		}
	//	CategoryDao cdao = CategoryDao.getInstance();
	//	ArrayList<CategoryVo> list1=new ArrayList<CategoryVo>();
	//	list1=cdao.leftList();
		
		//System.out.println(list2);
		//System.out.println(endPage);
	//	request.setAttribute("list1", list1);
		request.setAttribute("list2", list2);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("vose", vose);
		RequestDispatcher rd = request.getRequestDispatcher("/layout.jsp?page=searchList.jsp&left=noticeLeft.jsp");
		rd.forward(request, response);
	}

}
