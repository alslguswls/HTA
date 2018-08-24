package controller.ms;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ms.BoardDao;
import dao.ms.ReservationDao;
import vo.ms.BoardVo;
import vo.ms.ReservationVo;

@WebServlet("/detail.do")
public class DetailController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bnum = Integer.parseInt(request.getParameter("bnum"));
		BoardDao dao = new BoardDao();
		BoardVo vo = dao.detail(bnum);
		if(vo != null) {
			dao.hitup(bnum);
			String id = (String)request.getSession().getAttribute("id");
			ReservationDao rdao = new ReservationDao();
			if(rdao.select(new ReservationVo(0, bnum, id))) {
				request.setAttribute("resv", true);
			}else {
				request.setAttribute("resv", false);
			}
			request.setAttribute("vo", vo);
			System.out.println("detail.jsp로 이동");
			request.getRequestDispatcher("/layout.jsp?page=detail.jsp").forward(request, response);
		}else {
			request.setAttribute("errMsg", "오류로 인해 조회에 실패했습니다.");
			request.getRequestDispatcher("/layout.jsp?page=error.jsp").forward(request, response);
		}
	}
}
