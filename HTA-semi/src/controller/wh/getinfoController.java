package controller.wh;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.wh.MembersDao;
import vo.wh.MembersVo;

/**
2018-08-27	윤우현 	회원 수정 시 조회 기능 작성중
2018-09-02	윤우현 	관리자만 회원 관리 페이지 들어가도록 수정, 일반사용자 자신의 회원정보만 수정 가능하게 변경
 */
@WebServlet("/memberGetinfo.do")
public class getinfoController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원정보 수정을 관리자만 할 수 있음
		HttpSession session = request.getSession();	// 세션 객체를 얻어옴
		String checkId = (String) session.getAttribute("id");
		String checkLev = (String)session.getAttribute("isAdmin");
		
		if ( checkId == null ) {	// 로그인을 안했을때 아웃
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter pw = response.getWriter();
			pw.println("<script>");
			pw.println("alert('잘못된 접근입니다.');");
			pw.println("location.href = 'layout.jsp' ");
			pw.println("</script>");
			return;
		}
		
		String id = null;
		if ( "1".equals(checkLev)) {
			 id = request.getParameter("id");
		} else {
			id = checkId;
		}
		
		MembersDao dao = new MembersDao();
		MembersVo vo = dao.getinfo(id);
		
		if(vo != null) {
			request.setAttribute("id", vo.getId());
			request.setAttribute("pwd", vo.getPwd());
			request.setAttribute("email", vo.getEmail());
			request.setAttribute("phone", vo.getPhone());
			request.setAttribute("addr", vo.getAddr());
			request.setAttribute("coin", vo.getCoin());
			
			RequestDispatcher rd = request.getRequestDispatcher("layout.jsp?page=/update.jsp&left=admin.jsp");
			rd.forward(request, response);
		}else { 
			request.setAttribute("errMsg", "회원정보 조회 실패");
			RequestDispatcher rd = request.getRequestDispatcher("layout.jsp?page=error.jsp");
			rd.forward(request, response);
		}
	}

}
