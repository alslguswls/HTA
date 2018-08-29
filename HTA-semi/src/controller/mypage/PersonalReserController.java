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

/**
 * Servlet implementation class PersonalReserController
 */
@WebServlet("/perReser.do")
public class PersonalReserController extends HttpServlet {
		//예약목록 출력 관련 컨트롤러
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String perId = (String)session.getAttribute("id");
		ReservationVo vo = new ReservationVo();
		ReserDAO dao = new ReserDAO();
		ArrayList<ReservationVo> list = dao.reser(perId); 
		request.setAttribute("reserList", list);
		RequestDispatcher rd = request.getRequestDispatcher("/layout.jsp?page=personalRecord.jsp");
				rd.forward(request, response);
		
	}

}
