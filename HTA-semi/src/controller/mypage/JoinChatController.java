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

import board.vo.boardVo;
import dao.ms.ChatDao;
import dao.mypage.ReserDAO;
import vo.ms.BoardVo;
import vo.ms.ChatVo;

/**
 * Servlet implementation class JoinChatController
 */
@WebServlet("/joinchat.do")
public class JoinChatController extends HttpServlet {
		//경매생성목록(경매 참여자료) 출력 컨트롤러
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		//int n =Integer.parseInt(request.getParameter("n"));
		String charId = (String)session.getAttribute("id");
		ReserDAO dao = new ReserDAO();
		String spageNum=request.getParameter("pageNum");
		int pageNum=1;
		if (spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow=(pageNum-1)*10+1;
		int endRow=startRow +9;
		
		ArrayList<BoardVo> list = dao.chatTable(charId, startRow, endRow);
		int pageCount = (int)Math.ceil(dao.getChatCount(charId)/10.0);
		int startPage=((pageNum-1)/10*10)+1;
		int endPage=startPage + 9;
		if (endPage>pageCount) {
			endPage=pageCount;
		}
		//request.setAttribute("n", n);
		request.setAttribute("listChat", list);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("cate", 4);
		RequestDispatcher rd = request.getRequestDispatcher("/layout.jsp?page=personalChat.jsp&left=mypage.jsp");
		rd.forward(request, response);
		
	}

}
