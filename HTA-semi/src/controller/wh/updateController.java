package controller.wh;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.wh.MembersDao;
import javafx.scene.control.Alert;
import lib.SHA256Util;
import vo.wh.MembersVo;

/**
2018-08-27	윤우현 	회원 수정 기능 작성중
2018-08-28	윤우현 	회원 수정 기능 작성완료
2018-09-02	윤우현 	회원 수정시 패스워드 업데이트 기능 추가, alert 문구 수정
 */
@WebServlet("/memberUpdate.do")
public class updateController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		
		String input_pwd=request.getParameter("pwd");	// 입력된 평문 패스워드
		String pwd_enc = SHA256Util.generateSalt();	// 암호화 키
	        String pwd = SHA256Util.getEncrypt(input_pwd, pwd_enc);	// 평문 패스워드를 암호화
		
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String addr = request.getParameter("addr");
		Long coin = Long.parseLong(request.getParameter("coin"));
		
		MembersDao dao = new MembersDao();
		MembersVo vo = new MembersVo(id, pwd, email, phone, addr, 0, coin);
		vo.setPwd_enc(pwd_enc);
		int n = dao.update(vo);
		
		if(n>0) {
			String isAdmin = (String)request.getSession().getAttribute("isAdmin");	// 관리자 여부 확인

			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			
			PrintWriter pw = response.getWriter();
			pw.println("<script>");
			pw.println("alert('수정 되었습니다');");
			
			if ( "1".equals(isAdmin)) {
				pw.println("location.href = 'layout.jsp?page=memberList.do';");
			} else {
				pw.println("location.href = 'layout.jsp?page=memberGetinfo.do&left=mypage.jsp';");
			}
			
			pw.println("</script>");
			
//			String isAdmin = (String)request.getSession().getAttribute("isAdmin");	// 관리자 여부 확인
//			if ( "1".equals(isAdmin)) {
//				response.sendRedirect("layout.jsp?page=memberList.do");
//			} else {
//				response.sendRedirect("layout.jsp?page=memberGetinfo.do&left=mypage.jsp");
//			}
		}else {
			request.setAttribute("errMsg", "오류로 인해 회원 수정이 실패 하였습니다");
			RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
		}
	}
}
