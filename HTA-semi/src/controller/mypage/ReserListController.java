/*package controller.mypage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.mypage.ReserDAO;
import vo.notice.NoticeVO;


@WebServlet("/resernum.do")
public class ReserListController extends HttpServlet {
		//예약목록 리스트화 컨트롤러
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ReserDAO dao = new ReserDAO();
		String spageNum=request.getParameter("pageNum");
		int pageNum=1;
		if (spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		
		int startRow = (pageNum-1)*10+1;
		int endRow=startRow+9;
		//System.out.println(page);
		//ArrayList<NoticeVO> list = dao.all(startRow,endRow);//공지사항 전체 호출
		//int pageCount = (int)Math.ceil(dao.getCount()/10.0);//공지사항 전체 개수를 호출하는 dao
		int startPage = ((pageNum-1)/10*10)+1;
		int endPage= startPage + 9;
		if (endPage>pageCount) {
			endPage=pageCount;
		}
		//System.out.println(page);
		request.setAttribute("list22", list);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.getRequestDispatcher("/layout.jsp?page=notice.jsp").forward(request, response);
		
	}

}*/
