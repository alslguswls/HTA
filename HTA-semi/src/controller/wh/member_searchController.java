package controller.wh;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.wh.MembersDao;
import vo.wh.MembersVo;
import vo.wh.searchMemberVo;

/**
2018-08-30	회원관리 게시판 검색기능 추가
 */
@WebServlet("/memberSearch.do")
public class member_searchController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String searchSel = request.getParameter("searchSel");
		String searchText = request.getParameter("searchText");
		request.getSession().setAttribute("searchSel", searchSel);
		request.getSession().setAttribute("searchText", searchText);
		
		MembersDao dao = new MembersDao();
				
//		MembersVo vo = dao.search(searchSel, searchText, null, null);
//		MembersDao dao = MembersDao.instance();
		
		
		String spageNum = request.getParameter("pageNum");
		int pageNum=1;
		if(spageNum != null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow=(pageNum-1)*10 +1;
		int endRow=startRow+9;
		
		ArrayList<MembersVo> list=dao.search(searchSel, searchText, startRow, endRow);
		searchMemberVo smvo = new searchMemberVo(searchSel, searchText);
		// 전체 페이지 수 구하기. Math.ceil --> 올림
		int pageCount=(int)Math.ceil(dao.searchCount(searchSel, searchText)/10.0);
		// 시작 페이지 번호
		int startPage = ((pageNum-1)/10*10)+1;
		// 끝 페이지 번호★★
		int endPage = startPage+9;
		if(endPage>pageCount) {
			endPage=pageCount;
		}
		// 결과값(전체회원정보)를 스코프에 담기
		request.setAttribute("list",list);
		request.setAttribute("pageCount",pageCount);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("smvo", smvo);
		// 페이지로 이동하기
		System.out.println("aa");
		request.getRequestDispatcher("layout.jsp?page=member.jsp&left=admin.jsp").forward(request, response);
	}

}
