package controller.wh;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.wh.MembersDao;
import javafx.scene.control.Alert;
import vo.wh.MembersVo;

/**
2018-08-29	코인 콘트롤러 작성(윤우현)
 */
@WebServlet("/coin.do")
public class coinController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		
		// cmd에 내용이 없을 경우 getCoin 메소드 실행. cmd에 update가 있을 경우 update 메소드 실행
		if (cmd == null || cmd.equals("")) {
			getCoin(request, response);
		} else if (cmd.equals("update")) {
			update(request, response);
		}
	}
	
	// 세션에 id값이 있을경우 id와 coin 정보를 반환하는 메소드
	private void getCoin (HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		String id = (String)request.getSession().getAttribute("id");
		MembersDao dao = new MembersDao();
		MembersVo vo1 = dao.getinfo(id);
		
		if(vo1 != null) {
			request.setAttribute("vo1", vo1);
			request.setAttribute("cate", 1);
			RequestDispatcher rd = request.getRequestDispatcher("layout.jsp?page=addcoin.jsp");
			rd.forward(request, response);
		}else {
			System.out.println("타타타");
			response.sendRedirect("layout.jsp?page=login.jsp");
		}
	}
	
	// 코인을 업데이트 하기 위한 메소드
	private void update (HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		String id = (String)request.getSession().getAttribute("id");
		Long coin = Long.parseLong(request.getParameter("coin"));

		MembersDao dao = new MembersDao();
		int n = dao.coinUpdate(id, coin);
		
		if(n>0) {
			response.sendRedirect("layout.jsp?page=coin.do&left=mypage.jsp");
		}else {
			request.setAttribute("errMsg", "오류로 인해 코인 충전이 실패 하였습니다");
			RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
		}
	}
}
