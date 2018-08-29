package controller.mypage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.mypage.ReserDAO;
import vo.ms.ReservationVo;
import vo.notice.NoticeVO;

/**
 * Servlet implementation class PersonalReserController
 */
@WebServlet("/perReser.do")
public class PersonalReserController extends HttpServlet {
		//예약목록 출력 관련 컨트롤러
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String perId = (String)session.getAttribute("id");
		ReservationVo vo = new ReservationVo();
		ReserDAO dao = new ReserDAO();
		//ArrayList<ReservationVo> list = dao.reserAll(perId); 
		
		String spageNum=request.getParameter("pageNum");
		int pageNum=1;
		if (spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		
		int startRow = (pageNum-1)*10+1;
		int endRow=startRow+9;
		//System.out.println(page);
		//ArrayList<NoticeVO> list = dao.all(startRow,endRow);//공지사항 전체 호출
		ArrayList<ReservationVo> list = dao.reserAll(startRow, endRow,perId); 
		int pageCount = (int)Math.ceil(dao.getReserCount(perId)/10.0);//공지사항 전체 개수를 호출하는 dao
		int startPage = ((pageNum-1)/10*10)+1;
		int endPage= startPage + 9;
		if (endPage>pageCount) {
			endPage=pageCount;
		}
		//System.out.println(page);
		request.setAttribute("listReser", list);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		
		//request.setAttribute("reserList", list);
		RequestDispatcher rd = request.getRequestDispatcher("/layout.jsp?page=personalRecord.jsp&left=mypage.jsp");
				rd.forward(request, response);
		
	}

}
