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
import vo.ms.MpriceVo;
import vo.ms.ResultVo;

/**
 * Servlet implementation class JoinDealController
 */
@WebServlet("/joinDeal.do")
public class JoinDealController extends HttpServlet {
	//경매참여목록 출력 컨트롤러
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String dealId=(String)session.getAttribute("id");
		ReserDAO dao = new ReserDAO();
		String spageNum=request.getParameter("pageNum");
		int pageNum=1;
		if (spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		
		int startRow = (pageNum-1)*10+1;
		int endRow=startRow+9;
		
		ArrayList<ResultVo> list = dao.dealJoin(dealId, startRow, endRow);
		int pageCount = (int)Math.ceil(dao.getMyDealCount(dealId)/10.0);//공지사항 전체 개수를 호출하는 dao
		int startPage = ((pageNum-1)/10*10)+1;
		int endPage= startPage + 9;
		if (endPage>pageCount) {
			endPage=pageCount;
		}
		
		request.setAttribute("listDeal", list);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		RequestDispatcher rd = request.getRequestDispatcher("/layout.jsp?page=personalDeal.jsp&left=mypage.jsp");
		rd.forward(request, response);
	}

}
